package dream.consult.program.setting.upload.service;

import java.util.Map;

import common.bean.User;

public interface ExcelUploadService
{
    public Map upload(String excelTabNo, Map<String,Object> dataMap, User user) throws Exception;
}
