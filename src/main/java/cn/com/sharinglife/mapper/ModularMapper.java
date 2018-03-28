package cn.com.sharinglife.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by hell on 2018/2/22
 */
@Mapper
public interface ModularMapper {

    List<Modular> getAllModulars();

    Modular getModularById(Integer id);

    Integer addModular(Modular modular);

    void updateModular(Modular modular);

    void deleteModularById(Integer id);
}
