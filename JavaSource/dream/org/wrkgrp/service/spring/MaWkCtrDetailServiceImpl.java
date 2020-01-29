package dream.org.wrkgrp.service.spring;

import dream.org.wrkgrp.dao.MaWkCtrDetailDAO;
import dream.org.wrkgrp.dto.MaWkCtrDetailDTO;
import dream.org.wrkgrp.service.MaWkCtrDetailService;

/**
 * 작업그룹 - 상세 serviceimpl 
 * @author  
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="maWkCtrDetailServiceTarget"
 * @spring.txbn id="maWkCtrDetailService"
 * @spring.property name="maWkCtrDetailDAO" ref="maWkCtrDetailDAO"
 */
public class MaWkCtrDetailServiceImpl implements MaWkCtrDetailService
{
    private MaWkCtrDetailDAO maWkCtrDetailDAO = null;
    
    public MaWkCtrDetailDAO getMaWkCtrDetailDAO() {
		return maWkCtrDetailDAO;
	}

	public void setMaWkCtrDetailDAO(MaWkCtrDetailDAO maWkCtrDetailDAO) {
		this.maWkCtrDetailDAO = maWkCtrDetailDAO;
	}

	public MaWkCtrDetailDTO findDetail(String compNo, String wkCtrNo)throws Exception
    {
        return maWkCtrDetailDAO.findDetail(compNo, wkCtrNo);
    }
    
	public int insertDetail(MaWkCtrDetailDTO maWkCtrDetailDTO) throws Exception
    {        
        return maWkCtrDetailDAO.insertDetail(maWkCtrDetailDTO);
    }
	
	public int updateDetail(MaWkCtrDetailDTO maWkCtrDetailDTO) throws Exception
    {        
        return maWkCtrDetailDAO.updateDetail(maWkCtrDetailDTO);
    }
	
	public String validWkCtrNo(MaWkCtrDetailDTO maWkCtrDetailDTO) throws Exception
    {
        return maWkCtrDetailDAO.validWkCtrNo(maWkCtrDetailDTO);
    }
}
