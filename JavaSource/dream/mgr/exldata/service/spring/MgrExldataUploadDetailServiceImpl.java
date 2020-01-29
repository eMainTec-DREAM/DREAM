package dream.mgr.exldata.service.spring;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.bean.User;
import common.util.CommonUtil;
import dream.consult.program.setting.upload.dto.ConsultPgmSettingUpdFileDTO;
import dream.consult.program.setting.upload.service.ConsultPgmSettingUpdFileService;
import dream.mgr.exldata.dto.MgrExldataUploadDetailDTO;
import dream.mgr.exldata.service.MgrExldataUploadDetailService;

/**
 * ¿¢¼¿¾÷·Îµå - »ó¼¼ serviceimpl 
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="mgrExldataUploadDetailServiceTarget"
 * @spring.txbn id="mgrExldataUploadDetailService"
 */
public class MgrExldataUploadDetailServiceImpl implements MgrExldataUploadDetailService
{
	public int insertDetail(MgrExldataUploadDetailDTO mgrExldataUploadDetailDTO, String[] fileNames, User user) throws Exception
    {
	    ConsultPgmSettingUpdFileService consultPgmSettingUpdFileService = (ConsultPgmSettingUpdFileService) CommonUtil.getBean("consultPgmSettingUpdFileService", user);
        ConsultPgmSettingUpdFileDTO consultPgmSettingUpdFileDTO = (ConsultPgmSettingUpdFileDTO) convertDTO(mgrExldataUploadDetailDTO, ConsultPgmSettingUpdFileDTO.class);
        
        return consultPgmSettingUpdFileService.insertDetail(consultPgmSettingUpdFileDTO, fileNames, user);
    }
	
    public Map<String,String> uploadAutoFiles(MgrExldataUploadDetailDTO mgrExldataUploadDetailDTO, HttpServletRequest request, HttpServletResponse response, User user) throws Exception
    {
        ConsultPgmSettingUpdFileService consultPgmSettingUpdFileService = (ConsultPgmSettingUpdFileService) CommonUtil.getBean("consultPgmSettingUpdFileService", user);
        ConsultPgmSettingUpdFileDTO consultPgmSettingUpdFileDTO = (ConsultPgmSettingUpdFileDTO) convertDTO(mgrExldataUploadDetailDTO, ConsultPgmSettingUpdFileDTO.class);
        
        return consultPgmSettingUpdFileService.uploadAutoFiles(consultPgmSettingUpdFileDTO, request, response, user);
    }

    @Override
    public void deleteAutoFiles(MgrExldataUploadDetailDTO mgrExldataUploadDetailDTO, String[] deleteRows) throws Exception
    {
        ConsultPgmSettingUpdFileService consultPgmSettingUpdFileService = (ConsultPgmSettingUpdFileService) CommonUtil.getBean("consultPgmSettingUpdFileService", CommonUtil.getUser());
        ConsultPgmSettingUpdFileDTO consultPgmSettingUpdFileDTO = (ConsultPgmSettingUpdFileDTO) convertDTO(mgrExldataUploadDetailDTO, ConsultPgmSettingUpdFileDTO.class);
        
        consultPgmSettingUpdFileService.deleteAutoFiles(consultPgmSettingUpdFileDTO, deleteRows);
    }
    
    private Object convertDTO(Object fromDto, Class toDtoClass) throws InstantiationException, IllegalAccessException, SecurityException
    {
        Object obj = toDtoClass.newInstance();
        
        List<Field> fromFieldList = new ArrayList<Field>();
        List<Field> toFieldList = new ArrayList<Field>();
        
        fromFieldList = getAllFields(fromFieldList, fromDto.getClass());
        toFieldList = getAllFields(toFieldList, toDtoClass);
        
        for (Field fromField:fromFieldList)
        {
            String fromFieldName = fromField.getName();
            for(Field toField:toFieldList){
                String toFieldName = toField.getName();
                if(fromFieldName.equals(toFieldName)){
                    try
                    {
                        if(fromField.getType().equals(toField.getType())){
                            fromField.setAccessible(true);
                            toField.setAccessible(true);
                            toField.set(obj, fromField.get(fromDto));
                            break;
                        }
                    }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
            }
        }
        
        return obj;
    }
    
    private List<Field> getAllFields(List<Field> fields, Class<?> type)
    {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }
}
