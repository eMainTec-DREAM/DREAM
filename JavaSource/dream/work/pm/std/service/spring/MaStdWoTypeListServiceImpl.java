package dream.work.pm.std.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.pm.std.dao.MaStdWoTypeListDAO;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdWoTypeListDTO;
import dream.work.pm.std.service.MaStdWoTypeListService;

/**
 * 표준항목 리스트 - 목록 serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="maStdWoTypeListServiceTarget"
 * @spring.txbn id="maStdWoTypeListService"
 * @spring.property name="maStdWoTypeListDAO" ref="maStdWoTypeListDAO"
 */
public class MaStdWoTypeListServiceImpl implements MaStdWoTypeListService
{
    private MaStdWoTypeListDAO maStdWoTypeListDAO = null;

    public MaStdWoTypeListDAO getMaStdWoTypeListDAO() 
    {
		return maStdWoTypeListDAO;
	}

	public void setMaStdWoTypeListDAO(MaStdWoTypeListDAO maStdWoTypeListDAO) 
	{
		this.maStdWoTypeListDAO = maStdWoTypeListDAO;
	}

	public List findStdPointList(MaStdPointCommonDTO maStdPointCommonDTO,MaStdWoTypeListDTO maStdWoTypeListDTO, User loginUser)
    {      
        return maStdWoTypeListDAO.findStdPointList(maStdPointCommonDTO,maStdWoTypeListDTO, loginUser);
    }

	public int deleteList(String[] deleteRows, User loginUser) throws Exception
    {
        int result = 0;
        
        if(!deleteRows.equals(null))
        {
            for(String id : deleteRows)
            {
                result = result + maStdWoTypeListDAO.deleteList(id, loginUser);
            }
        }
        
        return result;
    }
}

