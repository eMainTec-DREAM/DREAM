package dream.work.pm.std.service.spring;

import common.bean.User;
import dream.work.pm.std.dao.MaStdPointDetailDAO;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdPointDetailDTO;
import dream.work.pm.std.dto.MaStdPointListDTO;
import dream.work.pm.std.service.MaStdPointDetailService;

/**
 * 표준항목 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="maStdPointDetailServiceTarget"
 * @spring.txbn id="maStdPointDetailService"
 * @spring.property name="maStdPointDetailDAO" ref="maStdPointDetailDAO"
 */
public class MaStdPointDetailServiceImpl implements MaStdPointDetailService
{
    private MaStdPointDetailDAO maStdPointDetailDAO = null;
    
    public MaStdPointDetailDAO getMaStdPointDetailDAO() 
    {
		return maStdPointDetailDAO;
	}

	public void setMaStdPointDetailDAO(MaStdPointDetailDAO maStdPointDetailDAO) 
	{
		this.maStdPointDetailDAO = maStdPointDetailDAO;
	}

	public MaStdPointDetailDTO findDetail(MaStdPointCommonDTO maStdPointCommonDTO,MaStdPointListDTO maStdPointListDTO, User loginUser)throws Exception
    {
        return maStdPointDetailDAO.findDetail(maStdPointCommonDTO, maStdPointListDTO, loginUser);
    }
    
	public int insertDetail(MaStdPointDetailDTO maStdPointDetailDTO, MaStdPointCommonDTO maStdPointCommonDTO, User loginUser) throws Exception
    {        
        return maStdPointDetailDAO.insertDetail(maStdPointDetailDTO, maStdPointCommonDTO, loginUser);
    }
	
	public int updateDetail(MaStdPointDetailDTO maStdPointDetailDTO, MaStdPointCommonDTO maStdPointCommonDTO, User loginUser) throws Exception
    {        
        return maStdPointDetailDAO.updateDetail(maStdPointDetailDTO, maStdPointCommonDTO, loginUser);
    }
	
}
