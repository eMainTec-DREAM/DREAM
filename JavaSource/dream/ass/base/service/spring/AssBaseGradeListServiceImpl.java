package dream.ass.base.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.ass.base.dao.AssBaseGradeListDAO;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBaseGradeDetailDTO;
import dream.ass.base.dto.AssBaseGradeListDTO;
import dream.ass.base.service.AssBaseGradeDetailService;
import dream.ass.base.service.AssBaseGradeListService;

/**
 * 등급기준 - List Service implements
 * @author kim21017
 * @version $Id: AssBaseGradeListServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="assBaseGradeListServiceTarget"
 * @spring.txbn id="assBaseGradeListService"
 * @spring.property name="assBaseGradeListDAO" ref="assBaseGradeListDAO"
 */
public class AssBaseGradeListServiceImpl implements AssBaseGradeListService
{
	private AssBaseGradeListDAO assBaseGradeListDAO = null;

	public List findList(AssBaseCommonDTO assBaseCommonDTO,AssBaseGradeListDTO assBaseGradeListDTO, User user) throws Exception
    {      
        return assBaseGradeListDAO.findList(assBaseCommonDTO,assBaseGradeListDTO,user);
    }

	public int deleteList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + assBaseGradeListDAO.deleteList(id, user);
            }
        return result;
    }
	public String findTotalCount(AssBaseCommonDTO assBaseCommonDTO,AssBaseGradeListDTO assBaseGradeListDTO, User user) throws Exception
    {      
        return assBaseGradeListDAO.findTotalCount(assBaseCommonDTO,assBaseGradeListDTO,user);
    }
	public AssBaseGradeListDAO getAssBaseGradeListDAO() {
		return assBaseGradeListDAO;
	}

	public void setAssBaseGradeListDAO(AssBaseGradeListDAO assBaseGradeListDAO) {
		this.assBaseGradeListDAO = assBaseGradeListDAO;
	}

	public void saveList(List<Map> gridList, User user) throws Exception 
	{
		AssBaseGradeDetailDTO assBaseGradeDetailDTO = null;
		AssBaseCommonDTO assBaseCommonDTO = null;
		AssBaseGradeDetailService assBaseGradeDetailService = (AssBaseGradeDetailService) CommonUtil.getBean("assBaseGradeDetailService");

        for(Map map : gridList)
        {
        	assBaseGradeDetailDTO = (AssBaseGradeDetailDTO)CommonUtil.makeDTO(map, AssBaseGradeDetailDTO.class);
        	assBaseCommonDTO = (AssBaseCommonDTO)CommonUtil.makeDTO(map, AssBaseCommonDTO.class);
        	
        	switch(assBaseGradeDetailDTO.getNativeeditor())
        	{
        		case "inserted":
        			break;
        		case "updated" : assBaseGradeDetailService.updateDetail(assBaseCommonDTO, assBaseGradeDetailDTO, user);
        			break;
        		case "deleted": String[] deleteRows = {assBaseGradeDetailDTO.getAssBaseGradeId()};
        		    this.deleteList(deleteRows, user);
        			break;
        	}
        	
        }
	}

}

