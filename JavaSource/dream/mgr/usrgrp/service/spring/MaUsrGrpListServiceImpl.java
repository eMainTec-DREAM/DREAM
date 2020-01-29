package dream.mgr.usrgrp.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.usrgrp.dao.MaUsrGrpListDAO;
import dream.mgr.usrgrp.dto.MaUsrGrpCommonDTO;
import dream.mgr.usrgrp.service.MaUsrGrpListService;

/**
 * ���ѱ׷� - ��� serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="maUsrGrpListServiceTarget"
 * @spring.txbn id="maUsrGrpListService"
 * @spring.property name="maUsrGrpListDAO" ref="maUsrGrpListDAO"
 */
public class MaUsrGrpListServiceImpl implements MaUsrGrpListService
{
    private MaUsrGrpListDAO maUsrGrpListDAO = null;

    public MaUsrGrpListDAO getMaUsrGrpListDAO() 
    {
		return maUsrGrpListDAO;
	}

	public void setMaUsrGrpListDAO(MaUsrGrpListDAO maUsrGrpListDAO) 
	{
		this.maUsrGrpListDAO = maUsrGrpListDAO;
	}

	public List findUseGrpList(MaUsrGrpCommonDTO maUsrGrpCommonDTO, User user)
    {      
        return maUsrGrpListDAO.findUsrGrpList(maUsrGrpCommonDTO, user);
    }
	
    public int deleteList(String compNo, String[] deleteRows) throws Exception
    {
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {   
                // ���ѿ� ���� "�޴� ����" ����
                result = result + maUsrGrpListDAO.deleteUsrGrpMenu(compNo, id);
                // ���� ���� 
                result = result + maUsrGrpListDAO.deleteUsrGrp(compNo, id);
            }
        
        return result;
    }

	@Override
	public String findTotalCount(MaUsrGrpCommonDTO maUsrGrpCommonDTO, User user)
	{
		return maUsrGrpListDAO.findTotalCount(maUsrGrpCommonDTO, user);
	}
}

