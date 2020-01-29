package dream.consult.program.table.service.spring;

import dream.consult.program.table.dao.MaTableColDetailDAO;
import dream.consult.program.table.dto.MaTableColDetailDTO;
import dream.consult.program.table.dto.MaTableColListDTO;
import dream.consult.program.table.dto.MaTableCommonDTO;
import dream.consult.program.table.service.MaTableColDetailService;

/**
 * 데이터 테이블 - 분류
 * @author kim2107
 * @version $Id: MaTableColDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maTableColDetailServiceTarget"
 * @spring.txbn id="maTableColDetailService"
 * @spring.property name="maTableColDetailDAO" ref="maTableColDetailDAO"
 */
public class MaTableColDetailServiceImpl implements MaTableColDetailService
{
    private MaTableColDetailDAO maTableColDetailDAO = null;
    
    public MaTableColDetailDAO getMaTableColDetailDAO() {
		return maTableColDetailDAO;
	}

	public void setMaTableColDetailDAO(MaTableColDetailDAO maTableColDetailDAO) {
		this.maTableColDetailDAO = maTableColDetailDAO;
	}

	public MaTableColDetailDTO findDetail(String tableMId, String tabColId, String lang)throws Exception
    {
        return maTableColDetailDAO.findDetail(tableMId, tabColId, lang);
    }
    
	public int updateDetail(MaTableColDetailDTO maTableColDetailDTO, MaTableCommonDTO maTableCommonDTO) throws Exception
    {        
        return maTableColDetailDAO.updateDetail(maTableColDetailDTO, maTableCommonDTO);
    }
	public int insertDetail(MaTableColDetailDTO maTableColDetailDTO, MaTableCommonDTO maTableCommonDTO) throws Exception
    {        
        return maTableColDetailDAO.insertDetail( maTableColDetailDTO, maTableCommonDTO);
    }
}
