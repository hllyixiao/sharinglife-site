package cn.com.sharinglife.service.impl;

import cn.com.sharinglife.mapper.LogsMapper;
import cn.com.sharinglife.pojo.Logs;
import cn.com.sharinglife.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hell on 2018/3/15
 */
@Service
public class LogsServiceImpl implements LogsService {

    @Autowired
    private LogsMapper logsMapper;
    @Override
    public void addLog(Logs logs) {
        logsMapper.addLog(logs);
    }
}
