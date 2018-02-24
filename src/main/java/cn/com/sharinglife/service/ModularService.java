package cn.com.sharinglife.service;


import cn.com.sharinglife.pojo.Modular;

import java.util.List;

/**
 * Created by hell on 2018/2/22
 */
public interface ModularService {

    List<Modular> getAllModulars();

    Modular getModularById(Integer id);

    Integer addModular(Modular modular);

    void updateModular(Modular modular);
}
