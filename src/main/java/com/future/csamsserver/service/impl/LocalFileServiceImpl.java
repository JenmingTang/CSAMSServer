package com.future.csamsserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.csamsserver.domain.LocalFile;
import com.future.csamsserver.service.LocalFileService;
import com.future.csamsserver.mapper.LocalFileMapper;
import org.springframework.stereotype.Service;

/**
* @author Jenming
* @description 针对表【local_file(统一文件表)】的数据库操作Service实现
* @createDate 2025-03-15 23:54:10
*/
@Service
public class LocalFileServiceImpl extends ServiceImpl<LocalFileMapper, LocalFile>
    implements LocalFileService{

}




