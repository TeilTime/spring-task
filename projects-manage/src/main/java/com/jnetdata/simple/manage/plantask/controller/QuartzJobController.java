package com.jnetdata.simple.manage.plantask.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.jnetdata.simple.core.controller.BaseController;
import com.jnetdata.simple.core.model.EntityId;
import com.jnetdata.simple.manage.plantask.CommonConstant;
import com.jnetdata.simple.manage.plantask.job.TaskBean;
import com.jnetdata.simple.manage.plantask.model.QuartzJob;
import com.jnetdata.simple.manage.plantask.service.IQuartzJobService;
import com.jnetdata.simple.manage.plantask.service.impl.QuartzJobServiceImpl;
import com.jnetdata.simple.manage.plantask.vo.PlanTaskVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.thenicesys.data.api.Pager;
import org.thenicesys.mybatis.impl.PropertyWrapper;
import org.thenicesys.web.JsonResult;


@Controller
@RequestMapping("/sys/quartzJob")
@ApiModel(value = "QuartzJobController", description = "计划任务配置")
public class QuartzJobController extends BaseController<Long,QuartzJob> {

	@Autowired
	private IQuartzJobService quartzJobService;

	@Autowired
	private TaskBean taskBean;

	/**
	 * 分页列表查询
	 * @return*/
	@ApiOperation(value = "查询", notes="根据vo查询")
	@PostMapping("/list")
	@ResponseBody
	public JsonResult<Pager<QuartzJob>> list(@RequestBody PlanTaskVo vo) {
		return doList(getService(), vo.getPager(), vo.getEntity());
	}

	/**
	 * 添加定时任务
	 * @return*/
	@ApiOperation(value = "添加计划任务信息", notes="添加计划任务信息")
	@PostMapping(value = "/add")
	@ResponseBody
	public JsonResult<EntityId<Long>> add(@RequestBody QuartzJob entity) {
		JsonResult<EntityId<Long>> result = null;
	 	List<QuartzJob> list = quartzJobService.findByJobClassName(entity.getJobClassName());
		if (list != null && list.size() > 0) {
			return renderSuccess("该定时任务类名已存在");
		}
		try {
			entity.setOldjobClassName(entity.getJobClassName());
			entity.setCreateTime(new Date());
			entity.setDelFlag(CommonConstant.DEL_FLAG_0);
			boolean i = getService().insertAllColumn(entity);
			boolean b = getService().saveAndScheduleJob(entity);
			if (b && i) {
				result = renderSuccess("定时任务创建并启动成功");
			}else if (b == false && i){
				result = renderSuccess("定时任务创建成功未启动");
			}else if(b && i == false){
				result = renderError("定时任务创建异常，请联系管理员！");
			}else{
				result = renderError("创建定时任务失败!");
			}
		} catch (Exception e) {
			result = renderError("创建定时任务失败，" + e.getMessage());
		}
		return result;
	}

	/**
	 * 更新定时任务
	 * @return*/
	@ApiOperation(value = "修改", notes="修改指定id对应的计划任务信息(只需要填计划任务中的id)")
	@PutMapping(value = "/{id}")
	@ResponseBody
	public JsonResult<Void> updateById(@ApiParam("任务id") @PathVariable("id") Long id,
									   @RequestBody QuartzJob entity) {
		JsonResult<Void> result = null;
		QuartzJob quartzJobEntity = quartzJobService.getById(entity.getId());
		if (quartzJobEntity == null) {
			result = renderError("未找到对应实体");
		} else {
			boolean b = true;
			try {
				entity.setModifyTime(new Date());
				entity.setOldjobClassName(quartzJobEntity.getJobClassName());
				b = getService().editAndScheduleJob(entity);
			} catch (SchedulerException e) {
				result = renderError("更新定时任务失败!");
				e.printStackTrace();
			}
			if (b){
				result = renderSuccess("更新定时任务成功!");
			}
		}
		return result;
	}

	/**
	 * 通过id删除
	 * @return*/
	@ApiOperation(value = "批量删除", notes="根据多个id删除计划任务信息")
	@PostMapping("/delByIds")
	@ResponseBody
	public JsonResult deleteBatchIds(@RequestBody Long[] ids) {
		JsonResult result = null;
		List<Long> list = Arrays.stream(ids).collect(Collectors.toList());
		List<String> strings = new ArrayList<String>();
		for(int i = 0 ; i < list.size() ; i ++){
			QuartzJob quartzJob = quartzJobService.getById(list.get(i));
			if (quartzJob == null) {
				result = renderError("未找到对应实体");
			} else {
				quartzJob.setStatus(CommonConstant.STATUS_DISABLE);
				getService().updateAllColumnById(quartzJob);
				taskBean.startCron();
				boolean b = quartzJobService.deleteAndStopJob(quartzJob);
				if (b) {
					result = renderSuccess("删除成功!");
				}
			}
		}
		return result;
	}


	/**
	 * 暂停定时任务
	 * @return*/
	@ApiOperation(value = "暂停定时任务", notes="暂停定时任务")
	@GetMapping("/pause")
	@ResponseBody
	public JsonResult pauseBatchIds(@RequestParam("ids") String ids) {
		JsonResult result = null;
		List<QuartzJob> list = getService().list(new PropertyWrapper<>(QuartzJob.class).in("id",ids));
		if(list.size() > 0){
			for(int i = 0 ; i < list.size() ; i++) {
				list.get(i).setStatus(CommonConstant.STATUS_DISABLE);
				list.get(i).setModifyTime(new Date());
				boolean b = getService().updateAllColumnById(list.get(i));
				if(b) {
					taskBean.startCron();
					result = renderSuccess("暂停定时任务成功!");
				}else {
					result = renderError("暂停定时任务异常，请联系管理员。");
				}
			}
		}
	 	return result;
	}

	/**
	 * 启动定时任务
	 * @return*/

	@ApiOperation(value = "恢复定时任务", notes="恢复定时任务")
	@GetMapping("/resume")
	@ResponseBody
	public JsonResult resumeBatchIds(@RequestParam("ids") String ids) {
		JsonResult result = null;
		List<QuartzJob> list = getService().list(new PropertyWrapper<>(QuartzJob.class).in("id",ids));
		if(list.size() > 0){
			for(int i = 0 ; i < list.size() ; i++) {
				list.get(i).setStatus(CommonConstant.STATUS_NORMAL);
				list.get(i).setModifyTime(new Date());
				boolean b = getService().updateAllColumnById(list.get(i));
				if(b) {
					taskBean.startCron();
					result = renderSuccess("恢复定时任务成功！");
				}else {
					result = renderError("恢复定时任务异常，请联系管理员。");
				}
			}
		}else {
			result = renderError("定时任务不存在");
		}
		return result;
	}

	/**
	 * 导出excel
	 * 	 * @return*/

	/*@RequestMapping(value = "/exportXls")
	public ModelAndView exportXls(HttpServletRequest request, QuartzJob quartzJob) {
		// Step.1 组装查询条件
		QueryWrapper<QuartzJob> queryWrapper = QueryGenerator.initQueryWrapper(quartzJob, request.getParameterMap());
		// Step.2 AutoPoi 导出Excel
		ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		List<QuartzJob> pageList = quartzJobService.list(queryWrapper);
		// 导出文件名称
		mv.addObject(NormalExcelConstants.FILE_NAME, "定时任务列表");
		mv.addObject(NormalExcelConstants.CLASS, QuartzJob.class);
		mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("定时任务列表数据", "导出人:Jeecg", "导出信息"));
		mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		return mv;
	}*/

	/**
	 * 通过excel导入数据
	 * @return*/

	/*@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<QuartzJob> listQuartzJobs = ExcelImportUtil.importExcel(file.getInputStream(), QuartzJob.class, params);
				for (QuartzJob quartzJobExcel : listQuartzJobs) {
					quartzJobService.save(quartzJobExcel);
				}
				return Result.ok("文件导入成功！数据行数：" + listQuartzJobs.size());
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return Result.error("文件导入失败！");
			} finally {
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return Result.error("文件导入失败！");
	}*/

	private IQuartzJobService getService(){
	 	return quartzJobService;
	}
}
