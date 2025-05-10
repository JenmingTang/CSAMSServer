package com.future.csamsserver.controller.view;

import cn.dev33.satoken.annotation.SaIgnore;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.future.csamsserver.domain.Activity;
import com.future.csamsserver.domain.Club;
import com.future.csamsserver.domain.LocalFile;
import com.future.csamsserver.enumeration.ApprovalStatusEnum;
import com.future.csamsserver.enumeration.DeletedEnum;
import com.future.csamsserver.enumeration.FileCategoryEnum;
import com.future.csamsserver.enumeration.TargetTypeEnum;
import com.future.csamsserver.mapper.ActivityMapper;
import com.future.csamsserver.mapper.ClubMapper;
import com.future.csamsserver.mapper.LocalFileMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @description
 * @dateTime 2025:03:26 00:01
 * @user Jenming
 */
/*
 * 视图获取静态资源
 * 考虑View控制器、SaToken、Web静态访问
 * */
@Controller
@Slf4j
public class ViewController {

    private final LocalFileMapper localFileMapper;
    private final ClubMapper clubMapper;
    private final ActivityMapper activityMapper;

    public ViewController(LocalFileMapper localFileMapper, ClubMapper clubMapper, ActivityMapper activityMapper) {
        this.localFileMapper = localFileMapper;
        this.clubMapper = clubMapper;
        this.activityMapper = activityMapper;
    }


    @GetMapping("/")// 忽略该方法的鉴权
    @SaIgnore
    public String root(Model model) {
        final LambdaQueryWrapper<LocalFile> wrapper = new LambdaQueryWrapper<>();
        /*
         * 获取特定必须字段需要sql
         * */
        wrapper
                .eq(true, LocalFile::getDeleted, DeletedEnum.NORMAL.getCode())
                .eq(true, LocalFile::getFileCategory, FileCategoryEnum.PRESS_RELEASE.getCode())
                .orderByDesc(true, LocalFile::getCreateTime)
                .last("LIMIT 20");
//        Page<LocalFile> page = new Page<>(1, 20);
//        localFileMapper.selectPage(page,wrapper);
//        List<LocalFile> list = page.getRecords();
        final List<LocalFile> localFileList = localFileMapper.selectList(wrapper);
        final List<LocalFile> localFileList2 = localFileList.stream().map(ViewController::setFileName).toList();
        model.addAttribute("newsItems", localFileList2);

        final LambdaQueryWrapper<Club> clubLambdaQueryWrapper = new LambdaQueryWrapper<>();
        clubLambdaQueryWrapper.eq(true, Club::getApprovalStatus, ApprovalStatusEnum.APPROVED.getCode());
        final List<Club> clubList = clubMapper.selectList(clubLambdaQueryWrapper);
        model.addAttribute("newsItems", localFileList2);
        model.addAttribute("clubList", clubList);
        return "index"; // 返回模板的相对路径（不带扩展名）
    }

    @GetMapping("/index")// 忽略该方法的鉴权
    @SaIgnore
    public void index(Model model) {
        root(model);
    }

    @GetMapping("/index2/{localFileId}")
// 忽略该方法的鉴权
    @SaIgnore
    public String show_press(@PathVariable("localFileId") long localFileId, Model model) {
        final LocalFile localFile = localFileMapper.selectById(localFileId);
        model.addAttribute("localFile", setFileName(localFile));
        return "index2"; // 返回模板的相对路径（不带扩展名）
    }

    private static LocalFile setFileName(LocalFile localFile) {
        final String fileName = localFile.getFileName();
        final String substring = fileName.substring(0, fileName.lastIndexOf("."));
        localFile.setFileName(substring);
        return localFile;
    }

    /*
http://localhost:8080/view_club/images4/0.jpg
访问静态资源会带上view_club，/index、/又不用带上？
    * */
    @GetMapping("/view_club/{clubId}")// 忽略该方法的鉴权
    @SaIgnore
    public String club_view(@PathVariable Long clubId, Model model) {
        final Club club = clubMapper.selectById(clubId);

        final List<LocalFile> clubImageList = getLocalFileList(clubId, TargetTypeEnum.CLUB, FileCategoryEnum.IMAGE);

        final LambdaQueryWrapper<Activity> activityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        activityLambdaQueryWrapper.eq(clubId != null, Activity::getClubId, clubId)
                .eq(true, Activity::getApprovalStatus, ApprovalStatusEnum.APPROVED.getCode());
        final List<Activity> activityList = activityMapper.selectList(activityLambdaQueryWrapper);

//        final LocalFile showPressRelease = localFileMapper.selectOne(wrapper2);
        /*
        * todo 每一个社团都必须要有展示新闻稿
        * */
        final LocalFile showPressRelease = getLocalFileList(clubId, TargetTypeEnum.CLUB, FileCategoryEnum.SHOW_PRESS_RELEASE).get(0);
        final List<LocalFile> pressReleaseList = getLocalFileList(clubId, TargetTypeEnum.CLUB, FileCategoryEnum.PRESS_RELEASE);


        model.addAttribute("club", club);
        model.addAttribute("clubImageList", clubImageList);
        model.addAttribute("activityList", activityList);
        model.addAttribute("showPressRelease", showPressRelease);
        model.addAttribute("pressReleaseList", pressReleaseList);
        log.info("clubId:{}", clubId);
        log.info("club:{}", club);
        log.info("clubImageList:{}", clubImageList);
        log.info("activityList:{}", activityList);
        log.info("showPressRelease:{}", showPressRelease);
        log.info("pressReleaseList:{}", pressReleaseList);
        return "club";
    }

    private List<LocalFile> getLocalFileList(Long clubId, TargetTypeEnum targetTypeEnum, FileCategoryEnum fileCategoryEnum) {
        final LambdaQueryWrapper<LocalFile> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(clubId != null, LocalFile::getTargetId, clubId)
                .eq(true, LocalFile::getTargetType, targetTypeEnum.getCode())
                .eq(true, LocalFile::getFileCategory, fileCategoryEnum.getCode())
                .eq(true, LocalFile::getDeleted, DeletedEnum.NORMAL.getCode());
        return localFileMapper.selectList(wrapper);
    }

    @GetMapping("/view_activity/{clubId}")// 忽略该方法的鉴权
    @SaIgnore
    public String activity_view(@PathVariable Long clubId, Model model) {
        Activity club = activityMapper.selectById(clubId);
        final List<LocalFile> clubImageList = getLocalFileList(clubId, TargetTypeEnum.ACTIVITY, FileCategoryEnum.IMAGE);
        final LocalFile showPressRelease = getLocalFileList(clubId, TargetTypeEnum.ACTIVITY, FileCategoryEnum.SHOW_PRESS_RELEASE).get(0);
        final List<LocalFile> pressReleaseList = getLocalFileList(clubId, TargetTypeEnum.ACTIVITY, FileCategoryEnum.PRESS_RELEASE);

        model.addAttribute("club", club);
        model.addAttribute("clubImageList", clubImageList);
        model.addAttribute("showPressRelease", showPressRelease);
        model.addAttribute("pressReleaseList", pressReleaseList);
        log.info("clubId:{}", clubId);
        log.info("club:{}", club);
        log.info("clubImageList:{}", clubImageList);
        log.info("showPressRelease:{}", showPressRelease);
        log.info("pressReleaseList:{}", pressReleaseList);
        return "activity";
    }


}
