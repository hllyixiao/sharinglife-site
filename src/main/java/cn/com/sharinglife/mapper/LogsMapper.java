package cn.com.sharinglife.mapper;

import cn.com.sharinglife.pojo.Logs;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by hell on 2018/3/15
 */
@Mapper
public interface LogsMapper {

    void addLog(Logs logs);
}
