package dream.consult.comp.user.service.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.consult.comp.user.dao.ConsultCompUsrGrpDetailDAO;
import dream.consult.comp.user.dto.ConsultCompUsrGrpCommonDTO;
import dream.consult.comp.user.dto.ConsultCompUsrGrpDetailDTO;
import dream.consult.comp.user.service.ConsultCompUsrGrpDetailService;


/**
 * 권한그룹 - 상세 serviceimpl 
 * @author  
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="consultCompUsrGrpDetailServiceTarget"
 * @spring.txbn id="consultCompUsrGrpDetailService"
 * @spring.property name="consultCompUsrGrpDetailDAO" ref="consultCompUsrGrpDetailDAO"
 */
public class ConsultCompUsrGrpDetailServiceImpl implements ConsultCompUsrGrpDetailService
{
    private ConsultCompUsrGrpDetailDAO consultCompUsrGrpDetailDAO = null;

    public ConsultCompUsrGrpDetailDAO getConsultCompUsrGrpDetailDAO() 
    {
		return consultCompUsrGrpDetailDAO;
	}

	public void setConsultCompUsrGrpDetailDAO(ConsultCompUsrGrpDetailDAO consultCompUsrGrpDetailDAO) 
	{
		this.consultCompUsrGrpDetailDAO = consultCompUsrGrpDetailDAO;
	}

	public ConsultCompUsrGrpDetailDTO findDetail(String compNo, String usrGrpId)throws Exception
    {
        return consultCompUsrGrpDetailDAO.findDetail(compNo, usrGrpId);
    }
    
	public int insertDetail(ConsultCompUsrGrpDetailDTO consultCompUsrGrpDetailDTO) throws Exception
    {        
	    int rtnNum = consultCompUsrGrpDetailDAO.insertDetail(consultCompUsrGrpDetailDTO);

        // 삭제 없이 저장한다. 
//	    saveUsrGrpList(consultCompUsrGrpDetailDTO, menuIds, true);
        
        return rtnNum;
    }
	
	public int updateDetail(ConsultCompUsrGrpDetailDTO consultCompUsrGrpDetailDTO) throws Exception
    {        
	    // 상세 정보 Update
	    int rtnNum = consultCompUsrGrpDetailDAO.updateDetail(consultCompUsrGrpDetailDTO);
	    
        // 기존 데이터 삭제후 저장.
//        saveUsrGrpList(consultCompUsrGrpDetailDTO, menuIds, false);
	    
        return rtnNum;
    }
	
	public String validUserGroup(ConsultCompUsrGrpDetailDTO consultCompUsrGrpDetailDTO) throws Exception
    {
        return consultCompUsrGrpDetailDAO.validUsrGrpNo(consultCompUsrGrpDetailDTO);
    }
	
    public List findMenuList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO)
    {      
        return consultCompUsrGrpDetailDAO.findMenuList(consultCompUsrGrpCommonDTO);
    }

    @Override
    public Map findMenuListForTree(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO, User user)
    {
        List resultList = consultCompUsrGrpDetailDAO.findMenuListForTree("", consultCompUsrGrpCommonDTO.getUsrGrpId(), consultCompUsrGrpCommonDTO.getCompNo(), user);

        List newResultList = new ArrayList();
        Map nMap = new HashMap();
        nMap.put("ID", "0");
        
        for(int i =0; resultList.size() > i; i++)
        {
            Map map = (Map)resultList.get(i);
            
            String menuId = String.valueOf(map.get("ID"));

            List subMenuList = consultCompUsrGrpDetailDAO.findMenuListForTree(menuId, consultCompUsrGrpCommonDTO.getUsrGrpId(), consultCompUsrGrpCommonDTO.getCompNo(), user);

            map.put("item", subMenuList);
            
            newResultList.add(map);
        }
        
        nMap.put("item",newResultList);
        
        return nMap;
    }    
    
    
}
