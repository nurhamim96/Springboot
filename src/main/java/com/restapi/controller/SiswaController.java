package com.restapi.controller;

import com.restapi.dto.SiswaDto;
import com.restapi.entity.Siswa;
import com.restapi.service.SiswaDao;
import com.restapi.util.CommonResponse;
import com.restapi.util.CommonResponseGenerator;
import com.restapi.util.JsonUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(path = "", produces = "application/json; charset=UTF-8")
public class SiswaController {

    @Autowired
    private SiswaDao siswaDao;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private CommonResponseGenerator conGen;

    private static final String SISWA_ADDR = "/siswa";
    private static final String SISWA_PATH_VARIABLE_ID = "id";
    private static final String SISWA_PATH_VARIABLE_NAME = "nama";
    private static final String SISWA_BY_ID_ADDR = SISWA_ADDR + "/{" + SISWA_PATH_VARIABLE_ID + "}";
    private static final String SISWA_BY_NAME_ADDR = SISWA_ADDR + SISWA_PATH_VARIABLE_NAME + "/{" + SISWA_PATH_VARIABLE_NAME + "}";

    @GetMapping(SISWA_ADDR)
    @CrossOrigin(origins = "*")
    public String getAllSiswa() throws Exception {
        List<SiswaDto> listSiswaDto = siswaDao.getAllSiswa().stream().map(s -> modelMapper.map(s, SiswaDto.class)).collect(Collectors.toList());
        CommonResponse<List<SiswaDto>> response = conGen.generateCommonResponse(listSiswaDto);
        return JsonUtil.generateJson(response);
    }

    @PostMapping(SISWA_ADDR)
    @CrossOrigin(origins = "*")
    public String addSiswa(@RequestBody Siswa siswa) throws Exception {
        SiswaDto siswaDto = modelMapper.map(siswaDao.addSiswa(siswa), SiswaDto.class);
        CommonResponse<SiswaDto> response = conGen.generateCommonResponse(siswaDto);
        return JsonUtil.generateJson(response);
    }

    @PutMapping(SISWA_BY_ID_ADDR)
    @CrossOrigin(origins = "*")
    public String updateSiswa(@PathVariable(value = "id") String id, @RequestBody Siswa updateSiswa) throws Exception {
        SiswaDto siswaDto = modelMapper.map(siswaDao.updateSiswa(id, updateSiswa), SiswaDto.class);
        CommonResponse<SiswaDto> response = conGen.generateCommonResponse(siswaDto);
        return JsonUtil.generateJson(response);
    }

    @DeleteMapping(SISWA_BY_ID_ADDR)
    @CrossOrigin(origins = "*")
    public String deleteSiswaById(@PathVariable("id") String id) throws Exception {
        String resp = siswaDao.deleteSiswaById(id);
        CommonResponse<String> response = conGen.generateCommonResponse(resp);
        return JsonUtil.generateJson(response);
    }

    @GetMapping(SISWA_BY_ID_ADDR)
    @CrossOrigin(origins = "*")
    public String getSiswaById(@PathVariable("id") String id) throws Exception {
        SiswaDto siswaDto = modelMapper.map(siswaDao.getSiswaById(id), SiswaDto.class);
        CommonResponse<SiswaDto> response = conGen.generateCommonResponse(siswaDto);
        return JsonUtil.generateJson(response);
    }

    @ExceptionHandler
    public String exception(Exception e) throws Exception {
        CommonResponse<String> resp = conGen.generateCommonResponse("X01", e.getMessage(), String.class);
        return JsonUtil.generateJson(resp);
    }

}
