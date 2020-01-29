package dream.tool.adj.service.spring;

import dream.tool.adj.dao.MaPttDisPartsDetailDAO;
import dream.tool.adj.dto.MaPttDisCommonDTO;
import dream.tool.adj.dto.MaPttDisPartsDetailDTO;
import dream.tool.adj.dto.MaPttDisPartsListDTO;
import dream.tool.adj.service.MaPttDisPartsDetailService;

/**
 * 
 * @author kim2107
 * @version $Id: MaPttDisPartsDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maPttDisPartsDetailServiceTarget"
 * @spring.txbn id="maPttDisPartsDetailService"
 * @spring.property name="maPttDisPartsDetailDAO" ref="maPttDisPartsDetailDAO"
 */
public class MaPttDisPartsDetailServiceImpl implements MaPttDisPartsDetailService
{
    private MaPttDisPartsDetailDAO maPttDisPartsDetailDAO = null;
    
    public MaPttDisPartsDetailDAO getMaPttDisPartsDetailDAO() {
		return maPttDisPartsDetailDAO;
	}

	public void setMaPttDisPartsDetailDAO(MaPttDisPartsDetailDAO maPttDisPartsDetailDAO) {
		this.maPttDisPartsDetailDAO = maPttDisPartsDetailDAO;
	}

	public MaPttDisPartsDetailDTO findDetail(MaPttDisPartsListDTO maPttDisPartsListDTO, MaPttDisCommonDTO maPttDisCommonDTO)throws Exception
    {
        return maPttDisPartsDetailDAO.findDetail(maPttDisPartsListDTO, maPttDisCommonDTO);
    }
    
	public int updateDetail(MaPttDisPartsDetailDTO maPttDisPartsDetailDTO, MaPttDisCommonDTO maPttDisCommonDTO) throws Exception
    {        
        return maPttDisPartsDetailDAO.updateDetail(maPttDisPartsDetailDTO, maPttDisCommonDTO);
    }
	public int insertDetail(MaPttDisPartsDetailDTO maPttDisPartsDetailDTO, MaPttDisCommonDTO maPttDisCommonDTO) throws Exception
    {        
        return maPttDisPartsDetailDAO.insertDetail( maPttDisPartsDetailDTO, maPttDisCommonDTO);
    }
}
