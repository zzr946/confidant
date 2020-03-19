package com.zzr.confidant.controller;

import com.zzr.confidant.dto.CollectDTO;
import com.zzr.confidant.dto.ResultDTO;
import com.zzr.confidant.service.CollectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @description 职位收藏表(Collect)表控制层
 * @author 赵志然
 * @date 2020-03-05 22:50:35
 */
@Api(tags = "用户收藏职位相关操作")
@Controller
public class CollectController {

    @Autowired
    private CollectService collectService;


    @ApiOperation(value = "搜藏职位", notes = "开发：赵志然")
    @PostMapping("/collectPosition/{userId}/{positionId}")
    @ResponseBody
    public ResultDTO collectPosition(@ApiParam(value = "当前登陆的用户ID") @PathVariable(value = "userId") String userId,
                                     @ApiParam(value = "用户收藏的职位") @PathVariable(value = "positionId") String positionId) {
        return collectService.collectPosition(userId,positionId);
    }

    @ApiOperation(value = "用户到职位收藏页面 查看收藏职位", notes = "开发：赵志然")
    @GetMapping("/selectCollections")
    public String selectCollections(@ApiParam(value = "当前登陆的用户ID") @RequestParam(value = "userId") String userId,
                                    HttpServletRequest request) {

         List<CollectDTO> list = collectService.selectCollections(userId);
        HttpSession session = request.getSession();
        session.setAttribute("collectionPosition",list);
        return "collections";
    }

    @ApiOperation(value = "取消收藏", notes = "开发：赵志然")
    @GetMapping("/cancle")
    public String cancleCollections(@ApiParam(value = "收藏ID") @RequestParam(value = "collectId") String collectId,
                                    @ApiParam(value = "用户ID") @RequestParam(value = "userId") String userId,
                                    HttpServletRequest request) {
        //删除职位
        collectService.delCollect(collectId);

        //删除完成后再次查询，返回到页面
        List<CollectDTO> list = collectService.selectCollections(userId);
        HttpSession session = request.getSession();
        session.setAttribute("collectionPosition",list);
        return "collections";
    }


}