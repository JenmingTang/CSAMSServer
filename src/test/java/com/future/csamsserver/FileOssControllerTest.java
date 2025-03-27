package com.future.csamsserver;

/**
 * @description
 * @dateTime 2025:03:13 00:15
 * @user Jenming
 */

// 基础类导入
import com.future.csamsserver.controller.file.FileOssController;
import com.future.csamsserver.enumeration.FileCategoryEnum;
import com.future.csamsserver.enumeration.TargetTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// 被测类相关导入
import com.future.csamsserver.domain.FileOss;
import com.future.csamsserver.mapper.FileOssMapper;
import cn.dev33.satoken.util.SaResult;

// Mockito相关导入
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class FileOssControllerTest {

    @Mock
    private FileOssMapper fileOssMapper;

    @InjectMocks
    private FileOssController fileOssController;

    private FileOss validFileOss;

    @BeforeEach
    void setUp() {
        validFileOss = new FileOss();
        validFileOss.setTargetType(TargetTypeEnum.CLUB);
        validFileOss.setTargetId(3L);
        validFileOss.setFileUrl("https://baomidou.com/assets/asset.cIbiVTt_.svg");
        validFileOss.setFileName("asset.cIbiVTt_");
        validFileOss.setFileType("jpg");
        validFileOss.setFileCategory(FileCategoryEnum.IMAGE);
        validFileOss.setFileSize(1024L);
    }

    /**
     * 测试正常插入/更新场景
     * 用例编号：1
     */
    @Test
    void whenInsertOrUpdateSuccess_thenReturnSuccessResult() {
        // 配置Mock行为
        when(fileOssMapper.insertOrUpdate(validFileOss)).thenReturn(true);

        // 执行被测方法
        SaResult result = fileOssController.insertOrUpdateFileOss(validFileOss);

        // 验证结果
        assertEquals(SaResult.CODE_SUCCESS, result.getCode());
        assertEquals(true, result.getData());
        verify(fileOssMapper, times(1)).insertOrUpdate(validFileOss);
    }

    /**
     * 测试数据库操作异常场景
     * 用例编号：2
     */
    @Test
    void whenDatabaseError_thenReturnErrorResult() {
        // 配置Mock抛出异常
        when(fileOssMapper.insertOrUpdate(validFileOss))
            .thenThrow(new RuntimeException("DB connection failed"));

        // 执行被测方法
        SaResult result = fileOssController.insertOrUpdateFileOss(validFileOss);

        // 验证结果
        assertEquals(SaResult.CODE_ERROR, result.getCode());
        assertNotNull(result.getMsg());
        verify(fileOssMapper, times(1)).insertOrUpdate(validFileOss);
    }
}
