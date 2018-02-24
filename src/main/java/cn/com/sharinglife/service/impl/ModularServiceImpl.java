package cn.com.sharinglife.service.impl;

import cn.com.sharinglife.mapper.ModularMapper;
import cn.com.sharinglife.pojo.Modular;
import cn.com.sharinglife.service.ModularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by hell on 2018/2/22
 */
@Service
public class ModularServiceImpl implements ModularService {

    @Autowired
    ModularMapper modularMapper;

    @Override
    public List<Modular> getAllModulars() {
        return modularMapper.getAllModulars();
    }

    @Override
    public Modular getModularById(Integer id) {
        return modularMapper.getModularById(id);
    }

    @Override
    public Integer addModular(Modular modular) {
        return modularMapper.addModular(modular);
    }

    @Override
    public void updateModular(Modular modular) {
        modularMapper.updateModular(modular);
    }
}
