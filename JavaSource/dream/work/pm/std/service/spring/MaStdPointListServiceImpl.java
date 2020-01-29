package dream.work.pm.std.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.pm.std.dao.MaStdPointListDAO;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdPointListDTO;
import dream.work.pm.std.service.MaStdPointListService;

/**
 * 표준항목 리스트 - 목록 serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="maStdPointListServiceTarget"
 * @spring.txbn id="maStdPointListService"
 * @spring.property name="maStdPointListDAO" ref="maStdPointListDAO"
 */
public class MaStdPointListServiceImpl implements MaStdPointListService
{
    private MaStdPointListDAO maStdPointListDAO = null;

    public MaStdPointListDAO getMaStdPointListDAO() 
    {
		return maStdPointListDAO;
	}

	public void setMaStdPointListDAO(MaStdPointListDAO maStdPointListDAO) 
	{
		this.maStdPointListDAO = maStdPointListDAO;
	}

	public List findStdPointList(MaStdPointCommonDTO maStdPointCommonDTO,MaStdPointListDTO maStdPointListDTO, User loginUser)
    {      
        return maStdPointListDAO.findStdPointList(maStdPointCommonDTO,maStdPointListDTO, loginUser);
    }

	public int deleteList(String[] deleteRows, User loginUser) throws Exception
    {
        int result = 0;
        
        if(!deleteRows.equals(null))
        {
            for(String id : deleteRows)
            {
                result = result + maStdPointListDAO.deleteList(id, loginUser);
            }
        }
        
        return result;
    }
}

