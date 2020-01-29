package dream.pers.priv.info.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.pers.priv.info.dao.PersPrivInfoMsgEmpListDAO;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.info.dto.PersPrivInfoMsgEmpDetailDTO;
import dream.pers.priv.info.service.PersPrivInfoMsgEmpDetailService;
import dream.pers.priv.info.service.PersPrivInfoMsgEmpListService;

/**
 * ¸ñ·Ï
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="persPrivInfoMsgEmpListServiceTarget"
 * @spring.txbn id="persPrivInfoMsgEmpListService"
 * @spring.property name="persPrivInfoMsgEmpListDAO" ref="persPrivInfoMsgEmpListDAO"
 */
public class PersPrivInfoMsgEmpListServiceImpl implements PersPrivInfoMsgEmpListService
{
    private PersPrivInfoMsgEmpListDAO persPrivInfoMsgEmpListDAO = null;


	public List findList(MaMyInfoCommonDTO maMyInfoCommonDTO, User user)
    {      
        return persPrivInfoMsgEmpListDAO.findList(maMyInfoCommonDTO, user);
    }

	public PersPrivInfoMsgEmpListDAO getPersPrivInfoMsgEmpListDAO() {
		return persPrivInfoMsgEmpListDAO;
	}

	public void setPersPrivInfoMsgEmpListDAO(PersPrivInfoMsgEmpListDAO persPrivInfoMsgEmpListDAO) {
		this.persPrivInfoMsgEmpListDAO = persPrivInfoMsgEmpListDAO;
	}
	
	public int deleteList(String[] deleteRows, User user) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + persPrivInfoMsgEmpListDAO.deleteList(id, user);
            }
        
        return result;
    }

	public String findTotalCount(MaMyInfoCommonDTO maMyInfoCommonDTO, User user) throws Exception 
	{
		return persPrivInfoMsgEmpListDAO.findTotalCount(maMyInfoCommonDTO, user);
	}

	@Override
	public void saveList(List<Map> gridList, User user) throws Exception 
	{
		PersPrivInfoMsgEmpDetailDTO persPrivInfoMsgEmpDetailDTO = null;
		MaMyInfoCommonDTO maMyInfoCommonDTO = null;
		PersPrivInfoMsgEmpDetailService persPrivInfoMsgEmpDetailService = (PersPrivInfoMsgEmpDetailService)CommonUtil.getBean("persPrivInfoMsgEmpDetailService", user);
		
		for(Map map : gridList)
        {
        	persPrivInfoMsgEmpDetailDTO = (PersPrivInfoMsgEmpDetailDTO)CommonUtil.makeDTO(map, PersPrivInfoMsgEmpDetailDTO.class);
        	maMyInfoCommonDTO = (MaMyInfoCommonDTO)CommonUtil.makeDTO(map, MaMyInfoCommonDTO.class);
        	
        	switch(persPrivInfoMsgEmpDetailDTO.getNativeeditor())
        	{
        		case "inserted":
        			break;
        		case "updated" : persPrivInfoMsgEmpDetailService.updateDetail(persPrivInfoMsgEmpDetailDTO, maMyInfoCommonDTO, user);
        			break;
        		case "deleted": persPrivInfoMsgEmpListDAO.deleteList(persPrivInfoMsgEmpDetailDTO.getMsgEmpSetId(), user);
        			break;
        	}
        	
        }
	}
}

