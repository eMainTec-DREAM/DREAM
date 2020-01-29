package dream.work.pm.std.service.spring;

import common.bean.User;
import dream.work.pm.std.dao.MaStdWorkDetailDAO;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdWorkDetailDTO;
import dream.work.pm.std.dto.MaStdWorkListDTO;
import dream.work.pm.std.service.MaStdWorkDetailService;

/**
 * 표준항목 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="maStdWorkDetailServiceTarget"
 * @spring.txbn id="maStdWorkDetailService"
 * @spring.property name="maStdWorkDetailDAO" ref="maStdWorkDetailDAO"
 */
public class MaStdWorkDetailServiceImpl implements MaStdWorkDetailService
{
    private MaStdWorkDetailDAO maStdWorkDetailDAO = null;
    
    public MaStdWorkDetailDAO getMaStdWorkDetailDAO() 
    {
		return maStdWorkDetailDAO;
	}

	public void setMaStdWorkDetailDAO(MaStdWorkDetailDAO maStdWorkDetailDAO) 
	{
		this.maStdWorkDetailDAO = maStdWorkDetailDAO;
	}

	public MaStdWorkDetailDTO findDetail(MaStdPointCommonDTO maStdPointCommonDTO,MaStdWorkListDTO maStdWorkListDTO, User loginUser)throws Exception
    {
        return maStdWorkDetailDAO.findDetail(maStdPointCommonDTO, maStdWorkListDTO, loginUser);
    }
    
	public int insertDetail(MaStdWorkDetailDTO maStdWorkDetailDTO, MaStdPointCommonDTO maStdPointCommonDTO, User loginUser) throws Exception
    {        
        return maStdWorkDetailDAO.insertDetail(maStdWorkDetailDTO, maStdPointCommonDTO, loginUser);
    }
	
	public int updateDetail(MaStdWorkDetailDTO maStdWorkDetailDTO, MaStdPointCommonDTO maStdPointCommonDTO, User loginUser) throws Exception
    {        
        return maStdWorkDetailDAO.updateDetail(maStdWorkDetailDTO, maStdPointCommonDTO, loginUser);
    }
	
}
