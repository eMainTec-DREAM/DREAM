package dream.work.pm.std.service.spring;

import common.bean.User;
import dream.work.pm.std.dao.MaStdPointHdrDetailDAO;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdPointHdrDetailDTO;
import dream.work.pm.std.service.MaStdPointHdrDetailService;

/**
 * 표준항목 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="maStdPointHdrDetailServiceTarget"
 * @spring.txbn id="maStdPointHdrDetailService"
 * @spring.property name="maStdPointHdrDetailDAO" ref="maStdPointHdrDetailDAO"
 */
public class MaStdPointHdrDetailServiceImpl implements MaStdPointHdrDetailService
{
    private MaStdPointHdrDetailDAO maStdPointHdrDetailDAO = null;
    
    public MaStdPointHdrDetailDAO getMaStdPointHdrDetailDAO() 
    {
		return maStdPointHdrDetailDAO;
	}

	public void setMaStdPointHdrDetailDAO(MaStdPointHdrDetailDAO maStdPointHdrDetailDAO) 
	{
		this.maStdPointHdrDetailDAO = maStdPointHdrDetailDAO;
	}

	public MaStdPointHdrDetailDTO findDetail(MaStdPointCommonDTO maStdPointCommonDTO, User loginUser)throws Exception
    {
        return maStdPointHdrDetailDAO.findDetail(maStdPointCommonDTO, loginUser);
    }
    
	public int insertDetail(MaStdPointHdrDetailDTO maStdPointHdrDetailDTO, User loginUser) throws Exception
    {        
        return maStdPointHdrDetailDAO.insertDetail(maStdPointHdrDetailDTO, loginUser);
    }
	
	public int updateDetail(MaStdPointHdrDetailDTO maStdPointHdrDetailDTO, User loginUser) throws Exception
    {        
        return maStdPointHdrDetailDAO.updateDetail(maStdPointHdrDetailDTO, loginUser);
    }
	public int confirmDetail(MaStdPointHdrDetailDTO maStdPointHdrDetailDTO) throws Exception
    {        
        return maStdPointHdrDetailDAO.confirmDetail(maStdPointHdrDetailDTO);
    }

	@Override
	public int insertRevisionHistAndUpdateMstr(MaStdPointHdrDetailDTO maStdPointHdrDetailDTO, User user) {
		String histId = maStdPointHdrDetailDAO.getNextSequence("SQAREVISIONHIST_ID");
		int result = 0;
		result+= maStdPointHdrDetailDAO.insertRevisionHist(maStdPointHdrDetailDTO, user, histId);
		result+= maStdPointHdrDetailDAO.updateStdPointMstrLastVersion(maStdPointHdrDetailDTO, user, histId);
		
		return result;
	}
}
