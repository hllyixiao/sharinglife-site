package cn.com.sharinglife.controller;

import cn.com.sharinglife.containapis.ModularApis;
import cn.com.sharinglife.pojo.Modular;
import cn.com.sharinglife.service.ModularService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hell on 2018/2/22
 */
@RestController
public class ModularController {

    private final Logger LOG = LoggerFactory.getLogger(ModularController.class);

    @Autowired
    ModularService modularService;

    @ApiOperation(value = "获取所有模块信息", notes = "获取所有模块信息")
    @GetMapping(value = ModularApis.GET_ALL_MODULAR_URL)
    public List<Modular> getAllModular() {
        return modularService.getAllModulars();
    }

    @ApiOperation(value = "获取模块信息", notes = "通过模块id获取模块信息")
    @GetMapping(value = ModularApis.GET_MODULAR_BY_ID_URL)
    public Modular getModularById(@RequestParam(value = "id") Integer id) {
        return modularService.getModularById(id);
    }

    @ApiOperation(value = "添加模块数据", notes = "添加模块数据")
    @PostMapping(value = ModularApis.ADD_MODULAR_URL)
    public void addModular(@RequestBody Modular modular) {
        if(modular.getId() != null){
            modularService.addModular(modular);
        }
    }

    @ApiOperation(value = "添加模块数据", notes = "添加模块数据")
    @PostMapping(value = ModularApis.UPDATE_MODULAR_URL)
    public void updateModular(@RequestBody Modular modular) {
        if(modular.getId() != null){
            modularService.addModular(modular);
        }
    }
}
