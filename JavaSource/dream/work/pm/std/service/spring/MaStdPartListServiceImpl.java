package dream.work.pm.std.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.pm.std.dao.MaStdPartListDAO;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdPartListDTO;
import dream.work.pm.std.service.MaStdPartListService;

/**
 * 표준항목 리스트 - 목록 serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="maStdPartListServiceTarget"
 * @spring.txbn id="maStdPartListService"
 * @spring.property name="maStdPartListDAO" ref="maStdPartListDAO"
 */
public class MaStdPartListServiceImpl implements MaStdPartListService
{
    private MaStdPartListDAO maStdPartListDAO = null;

    public MaStdPartListDAO getMaStdPartListDAO() 
    {
		return maStdPartListDAO;
	}

	public void setMaStdPartListDAO(MaStdPartListDAO maStdPartListDAO) 
	{
		this.maStdPartListDAO = maStdPartListDAO;
	}

	public List findStdPointList(MaStdPointCommonDTO maStdPointCommonDTO,MaStdPartListDTO maStdPartListDTO, User loginUser)
    {      
        return maStdPartListDAO.findStdPointList(maStdPointCommonDTO,maStdPartListDTO, loginUser);
    }

	public int deleteList(String[] deleteRows, User loginUser) throws Exception
    {
        int result = 0;
        
        if(!deleteRows.equals(null))
        {
            for(String id : deleteRows)
            {
                result = result + maStdPartListDAO.deleteList(id, loginUser);
            }
        }
        
        return result;
    }
}

