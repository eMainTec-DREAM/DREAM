package dream.work.pm.std.service.spring;

import common.bean.User;
import dream.work.pm.std.dao.MaStdPartDetailDAO;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdPartDetailDTO;
import dream.work.pm.std.dto.MaStdPartListDTO;
import dream.work.pm.std.service.MaStdPartDetailService;

/**
 * 표준항목 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="maStdPartDetailServiceTarget"
 * @spring.txbn id="maStdPartDetailService"
 * @spring.property name="maStdPartDetailDAO" ref="maStdPartDetailDAO"
 */
public class MaStdPartDetailServiceImpl implements MaStdPartDetailService
{
    private MaStdPartDetailDAO maStdPartDetailDAO = null;
    
    public MaStdPartDetailDAO getMaStdPartDetailDAO() 
    {
		return maStdPartDetailDAO;
	}

	public void setMaStdPartDetailDAO(MaStdPartDetailDAO maStdPartDetailDAO) 
	{
		this.maStdPartDetailDAO = maStdPartDetailDAO;
	}

	public MaStdPartDetailDTO findDetail(MaStdPointCommonDTO maStdPointCommonDTO,MaStdPartListDTO maStdPartListDTO, User loginUser)throws Exception
    {
        return maStdPartDetailDAO.findDetail(maStdPointCommonDTO, maStdPartListDTO, loginUser);
    }
    
	public int insertDetail(MaStdPartDetailDTO maStdPartDetailDTO, MaStdPointCommonDTO maStdPointCommonDTO, User loginUser) throws Exception
    {        
        return maStdPartDetailDAO.insertDetail(maStdPartDetailDTO, maStdPointCommonDTO, loginUser);
    }
	
	public int updateDetail(MaStdPartDetailDTO maStdPartDetailDTO, MaStdPointCommonDTO maStdPointCommonDTO, User loginUser) throws Exception
    {        
        return maStdPartDetailDAO.updateDetail(maStdPartDetailDTO, maStdPointCommonDTO, loginUser);
    }
	
}
