package dream.work.pm.std.service.spring;

import common.bean.User;
import dream.work.pm.std.dao.MaStdWoTypeDetailDAO;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdWoTypeDetailDTO;
import dream.work.pm.std.dto.MaStdWoTypeListDTO;
import dream.work.pm.std.service.MaStdWoTypeDetailService;

/**
 * 표준항목 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="maStdWoTypeDetailServiceTarget"
 * @spring.txbn id="maStdWoTypeDetailService"
 * @spring.property name="maStdWoTypeDetailDAO" ref="maStdWoTypeDetailDAO"
 */
public class MaStdWoTypeDetailServiceImpl implements MaStdWoTypeDetailService
{
    private MaStdWoTypeDetailDAO maStdWoTypeDetailDAO = null;
    
    public MaStdWoTypeDetailDAO getMaStdWoTypeDetailDAO() 
    {
		return maStdWoTypeDetailDAO;
	}

	public void setMaStdWoTypeDetailDAO(MaStdWoTypeDetailDAO maStdWoTypeDetailDAO) 
	{
		this.maStdWoTypeDetailDAO = maStdWoTypeDetailDAO;
	}

	public MaStdWoTypeDetailDTO findDetail(MaStdPointCommonDTO maStdPointCommonDTO,MaStdWoTypeListDTO maStdWoTypeListDTO, User loginUser)throws Exception
    {
        return maStdWoTypeDetailDAO.findDetail(maStdPointCommonDTO, maStdWoTypeListDTO, loginUser);
    }
    
	public int insertDetail(MaStdWoTypeDetailDTO maStdWoTypeDetailDTO, MaStdPointCommonDTO maStdPointCommonDTO, User loginUser) throws Exception
    {        
        return maStdWoTypeDetailDAO.insertDetail(maStdWoTypeDetailDTO, maStdPointCommonDTO, loginUser);
    }
	
	public int updateDetail(MaStdWoTypeDetailDTO maStdWoTypeDetailDTO, MaStdPointCommonDTO maStdPointCommonDTO, User loginUser) throws Exception
    {        
        return maStdWoTypeDetailDAO.updateDetail(maStdWoTypeDetailDTO, maStdPointCommonDTO, loginUser);
    }
	
}
