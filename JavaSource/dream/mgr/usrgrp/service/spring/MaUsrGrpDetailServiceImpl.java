package dream.mgr.usrgrp.service.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.mgr.usrgrp.dao.MaUsrGrpDetailDAO;
import dream.mgr.usrgrp.dao.MaUsrGrpMenuDAO;
import dream.mgr.usrgrp.dto.MaUsrGrpCommonDTO;
import dream.mgr.usrgrp.dto.MaUsrGrpDetailDTO;
import dream.mgr.usrgrp.dto.MaUsrGrpMenuDTO;
import dream.mgr.usrgrp.service.MaUsrGrpDetailService;
import dream.mgr.usrgrp.service.MaUsrGrpListService;

/**
 * ���ѱ׷� - �� serviceimpl 
 * @author  
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="maUsrGrpDetailServiceTarget"
 * @spring.txbn id="maUsrGrpDetailService"
 * @spring.property name="maUsrGrpDetailDAO" ref="maUsrGrpDetailDAO"
 * @spring.property name="maUsrGrpMenuDAO" ref="maUsrGrpMenuDAO"
 */
public class MaUsrGrpDetailServiceImpl implements MaUsrGrpDetailService
{
    private MaUsrGrpDetailDAO maUsrGrpDetailDAO = null;
    private MaUsrGrpMenuDAO maUsrGrpMenuDAO = null;
    
    public MaUsrGrpMenuDAO getMaUsrGrpMenuDAO()
    {
        return maUsrGrpMenuDAO;
    }

    public void setMaUsrGrpMenuDAO(MaUsrGrpMenuDAO maUsrGrpMenuDAO)
    {
        this.maUsrGrpMenuDAO = maUsrGrpMenuDAO;
    }

    public MaUsrGrpDetailDAO getMaUsrGrpDetailDAO() 
    {
		return maUsrGrpDetailDAO;
	}

	public void setMaUsrGrpDetailDAO(MaUsrGrpDetailDAO maUsrGrpDetailDAO) 
	{
		this.maUsrGrpDetailDAO = maUsrGrpDetailDAO;
	}

	public MaUsrGrpDetailDTO findDetail(MaUsrGrpCommonDTO maUsrGrpCommonDTO, User user)throws Exception
    {
	    MaUsrGrpListService maUsrGrpListService = (MaUsrGrpListService) CommonUtil.getBean("maUsrGrpListService", user);
	    return (MaUsrGrpDetailDTO) CommonUtil.makeDetailFromList(maUsrGrpListService.findUseGrpList(maUsrGrpCommonDTO, user), new MaUsrGrpDetailDTO());
    }
    
	public int insertDetail(MaUsrGrpDetailDTO maUsrGrpDetailDTO, String[] menuIds) throws Exception
    {        
	    int rtnNum = maUsrGrpDetailDAO.insertDetail(maUsrGrpDetailDTO);

        // ���� ���� �����Ѵ�. 
	    saveUsrGrpList(maUsrGrpDetailDTO, menuIds, true);
        
        return rtnNum;
    }
	
	public int updateDetail(MaUsrGrpDetailDTO maUsrGrpDetailDTO, String[] menuIds) throws Exception
    {        
	    // �� ���� Update
	    int rtnNum = maUsrGrpDetailDAO.updateDetail(maUsrGrpDetailDTO);
	    
        // ���� ������ ������ ����.
        saveUsrGrpList(maUsrGrpDetailDTO, menuIds, false);
	    
        return rtnNum;
    }
	
	public String validUserGroup(MaUsrGrpDetailDTO maUsrGrpDetailDTO) throws Exception
    {
        return maUsrGrpDetailDAO.validUsrGrpNo(maUsrGrpDetailDTO);
    }
	
    public List findMenuList(MaUsrGrpCommonDTO maUsrGrpCommonDTO)
    {      
        return maUsrGrpDetailDAO.findMenuList(maUsrGrpCommonDTO);
    }
    
    public int saveUsrGrpList(MaUsrGrpDetailDTO maUsrGrpDetailDTO, String[] menuIds, boolean isCreateUsrGrp) throws Exception
    {
        int result = 0;
        MaUsrGrpMenuDTO maUsrGrpMenuDTO = null;

        if(menuIds == null || !"Y".equals(maUsrGrpDetailDTO.getIsUpdateMenu())) return result;
            
        // ��ü ������ ����  - ���ο� ���� ������ ��� Delete ���� �ʴ´�. 
        if(!isCreateUsrGrp)
        {
            maUsrGrpMenuDAO.deleteUserGrpMenuLsit(maUsrGrpDetailDTO.getCompNo(), maUsrGrpDetailDTO.getUsrGrpId());
        }
        
        // sheet�� row ����ŭ �ݺ��Ѵ�. 
        for (int i =0; i < menuIds.length; i++)
        {
            maUsrGrpMenuDTO = new MaUsrGrpMenuDTO();
            maUsrGrpMenuDTO.setCompNo(maUsrGrpDetailDTO.getCompNo());
            maUsrGrpMenuDTO.setUsrGrpId(maUsrGrpDetailDTO.getUsrGrpId());
            maUsrGrpMenuDTO.setMenuId(menuIds[i]);
            
            maUsrGrpMenuDAO.insertUserGrpMenu(maUsrGrpMenuDTO);
            
        }
        return result;
    }

    @Override
    public Map findMenuListForTree(MaUsrGrpCommonDTO maUsrGrpCommonDTO, User user)
    {
        List resultList = maUsrGrpMenuDAO.findMenuListForTree("", maUsrGrpCommonDTO.getUsrGrpId(), maUsrGrpCommonDTO.getCompNo(), user);

        List newResultList = new ArrayList();
        Map nMap = new HashMap();
        nMap.put("ID", "0");
        
        for(int i =0; resultList.size() > i; i++)
        {
            Map map = (Map)resultList.get(i);
            
            String menuId = String.valueOf(map.get("ID"));

            List subMenuList = maUsrGrpMenuDAO.findMenuListForTree(menuId, maUsrGrpCommonDTO.getUsrGrpId(), maUsrGrpCommonDTO.getCompNo(), user);

            map.put("item", subMenuList);
            
            newResultList.add(map);
        }
        
        nMap.put("item",newResultList);
        
        return nMap;
    }    
    
    
}
