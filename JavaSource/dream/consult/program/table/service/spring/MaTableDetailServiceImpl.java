package dream.consult.program.table.service.spring;

import dream.consult.program.table.dao.MaTableDetailDAO;
import dream.consult.program.table.dto.MaTableDetailDTO;
import dream.consult.program.table.service.MaTableDetailService;

/**
 * 데이터 테이블 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: MaTableDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maTableDetailServiceTarget"
 * @spring.txbn id="maTableDetailService"
 * @spring.property name="maTableDetailDAO" ref="maTableDetailDAO"
 */
public class MaTableDetailServiceImpl implements MaTableDetailService
{
    private MaTableDetailDAO maTableDetailDAO = null;
    
    public MaTableDetailDAO getMaTableDetailDAO() {
		return maTableDetailDAO;
	}

	public void setMaTableDetailDAO(MaTableDetailDAO maTableDetailDAO) {
		this.maTableDetailDAO = maTableDetailDAO;
	}

	public MaTableDetailDTO findDetail(String tableMId, String lang)throws Exception
    {
        return maTableDetailDAO.findDetail(tableMId,lang);
    }
	
	public int updateDetail(MaTableDetailDTO maTableDetailDTO) throws Exception
    {        
        return maTableDetailDAO.updateDetail(maTableDetailDTO);
    }
	public int insertDetail(MaTableDetailDTO maTableDetailDTO) throws Exception
    {        
        return maTableDetailDAO.insertDetail(maTableDetailDTO);
    }
}
