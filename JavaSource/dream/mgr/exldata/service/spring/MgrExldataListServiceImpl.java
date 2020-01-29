package dream.mgr.exldata.service.spring;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.bean.User;
import common.util.CommonUtil;
import dream.consult.program.setting.upload.dto.ConsultPgmSettingUpdDTO;
import dream.consult.program.setting.upload.service.ConsultPgmSettingUpdService;
import dream.mgr.exldata.dto.MgrExldataCommonDTO;
import dream.mgr.exldata.service.MgrExldataListService;

/**
 * Excel Data Upload - List Service implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="mgrExldataListServiceTarget"
 * @spring.txbn id="mgrExldataListService"
 */
public class MgrExldataListServiceImpl implements MgrExldataListService
{
	public List findExldataList(MgrExldataCommonDTO mgrExldataCommonDTO, User user) throws Exception
    {      
	    ConsultPgmSettingUpdService consultPgmSettingUpdService = (ConsultPgmSettingUpdService) CommonUtil.getBean("consultPgmSettingUpdService", user);
	    ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO = (ConsultPgmSettingUpdDTO) convertDTO(mgrExldataCommonDTO, ConsultPgmSettingUpdDTO.class);
	    
	    return consultPgmSettingUpdService.findExldataList(consultPgmSettingUpdDTO, user);
    }

	public int deleteExldataList( String table, String[] deleteRows, User user) throws Exception
	{
	    ConsultPgmSettingUpdService consultPgmSettingUpdService = (ConsultPgmSettingUpdService) CommonUtil.getBean("consultPgmSettingUpdService", user);
        
        return consultPgmSettingUpdService.deleteExldataList(table, deleteRows, user);
    }
	
	public String findTotalCount(MgrExldataCommonDTO mgrExldataCommonDTO,User user) throws Exception
    {
	    ConsultPgmSettingUpdService consultPgmSettingUpdService = (ConsultPgmSettingUpdService) CommonUtil.getBean("consultPgmSettingUpdService", user);
        ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO = (ConsultPgmSettingUpdDTO) convertDTO(mgrExldataCommonDTO, ConsultPgmSettingUpdDTO.class);
        
        return consultPgmSettingUpdService.findTotalCount(consultPgmSettingUpdDTO, user);
    }
	
	public List getDummyHeader(MgrExldataCommonDTO mgrExldataCommonDTO, User user) throws Exception {
	    ConsultPgmSettingUpdService consultPgmSettingUpdService = (ConsultPgmSettingUpdService) CommonUtil.getBean("consultPgmSettingUpdService", user);
        ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO = (ConsultPgmSettingUpdDTO) convertDTO(mgrExldataCommonDTO, ConsultPgmSettingUpdDTO.class);
        
	    return consultPgmSettingUpdService.getDummyHeader(consultPgmSettingUpdDTO, user);
	}
	
	public List findExcelTemplateData(MgrExldataCommonDTO mgrExldataCommonDTO, User user) throws Exception
    {
        ConsultPgmSettingUpdService consultPgmSettingUpdService = (ConsultPgmSettingUpdService) CommonUtil.getBean("consultPgmSettingUpdService", user);
        ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO = (ConsultPgmSettingUpdDTO) convertDTO(mgrExldataCommonDTO, ConsultPgmSettingUpdDTO.class);
        
        return consultPgmSettingUpdService.findExcelTemplateData(consultPgmSettingUpdDTO, user);
    }

	@Override
	public void uploadData(MgrExldataCommonDTO mgrExldataCommonDTO, User user) throws Exception {
	    ConsultPgmSettingUpdService consultPgmSettingUpdService = (ConsultPgmSettingUpdService) CommonUtil.getBean("consultPgmSettingUpdService", user);
        ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO = (ConsultPgmSettingUpdDTO) convertDTO(mgrExldataCommonDTO, ConsultPgmSettingUpdDTO.class);
        
        consultPgmSettingUpdService.uploadData(consultPgmSettingUpdDTO, user);
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

