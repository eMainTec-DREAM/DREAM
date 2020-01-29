package dream.work.pm.std.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.pm.std.dao.MaStdPointHdrListDAO;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.service.MaStdPointHdrListService;

/**
 * 표준항목 - 목록 serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="maStdPointHdrListServiceTarget"
 * @spring.txbn id="maStdPointHdrListService"
 * @spring.property name="maStdPointHdrListDAO" ref="maStdPointHdrListDAO"
 */
public class MaStdPointHdrListServiceImpl implements MaStdPointHdrListService
{
    private MaStdPointHdrListDAO maStdPointHdrListDAO = null;

    public MaStdPointHdrListDAO getMaStdPointHdrListDAO() 
    {
		return maStdPointHdrListDAO;
	}

	public void setMaStdPointHdrListDAO(MaStdPointHdrListDAO maStdPointHdrListDAO) 
	{
		this.maStdPointHdrListDAO = maStdPointHdrListDAO;
	}

	public List findStdPointHdrList(MaStdPointCommonDTO maStdPointCommonDTO, User loginUser)
    {      
        return maStdPointHdrListDAO.findStdPointHdrList(maStdPointCommonDTO, loginUser);
    }

	public int deleteList(String[] deleteRows, User loginUser) throws Exception
    {
        int result = 0;
        
        if(!deleteRows.equals(null))
        {
            for(String id : deleteRows)
            {
                result = result + maStdPointHdrListDAO.deleteList(id, loginUser);
            }
        }
        
        return result;
    }
	
	public String findTotalCount(MaStdPointCommonDTO maStdPointCommonDTO, User loginUser) throws Exception 
	{
		return maStdPointHdrListDAO.findTotalCount(maStdPointCommonDTO, loginUser);
	}
}

