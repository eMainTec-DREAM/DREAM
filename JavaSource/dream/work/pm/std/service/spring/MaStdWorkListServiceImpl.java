package dream.work.pm.std.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.pm.std.dao.MaStdWorkListDAO;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdWorkListDTO;
import dream.work.pm.std.service.MaStdWorkListService;

/**
 * 표준항목 리스트 - 목록 serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="maStdWorkListServiceTarget"
 * @spring.txbn id="maStdWorkListService"
 * @spring.property name="maStdWorkListDAO" ref="maStdWorkListDAO"
 */
public class MaStdWorkListServiceImpl implements MaStdWorkListService
{
    private MaStdWorkListDAO maStdWorkListDAO = null;

    public MaStdWorkListDAO getMaStdWorkListDAO() 
    {
		return maStdWorkListDAO;
	}

	public void setMaStdWorkListDAO(MaStdWorkListDAO maStdWorkListDAO) 
	{
		this.maStdWorkListDAO = maStdWorkListDAO;
	}

	public List findStdPointList(MaStdPointCommonDTO maStdPointCommonDTO,MaStdWorkListDTO maStdWorkListDTO, User loginUser)
    {      
        return maStdWorkListDAO.findStdPointList(maStdPointCommonDTO,maStdWorkListDTO, loginUser);
    }

	public int deleteList(String[] deleteRows, User loginUser) throws Exception
    {
        int result = 0;
        
        if(!deleteRows.equals(null))
        {
            for(String id : deleteRows)
            {
                result = result + maStdWorkListDAO.deleteList(id, loginUser);
            }
        }
        
        return result;
    }
}

