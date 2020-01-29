package dream.part.list.service;

import common.bean.User;
import dream.part.list.dto.PartListBinDetailDTO;
import dream.part.list.dto.PartListBinListDTO;
/**
 * ��ǰâ�� ������ġ - Detail Service
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 */
public interface PartListBinDetailService
{    
	/**
	 * ��ǰâ�� ������ġ DETAIL
     * @author cjscjs9
	 * @param partListBinListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public PartListBinDetailDTO findPtWhBinDetail(PartListBinListDTO partListBinListDTO, User user) throws Exception;
	/**
	 * INSERT ��ǰâ�� ������ġ DETAIL
     * @author cjscjs9
	 * @param partListBinDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertPtWhBinDetail(PartListBinDetailDTO partListBinDetailDTO, User user) throws Exception;
    /**
     * UPDATE ��ǰâ�� ������ġ DETAIL
     * @author cjscjs9
     * @param partListBinDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updatePtWhBinDetail(PartListBinDetailDTO partListBinDetailDTO, User user) throws Exception;
    
    /**
     * valid empNo ��� ��ȣ 
     * @author cjscjs9
     * @version $Id:$
     * @since   1.0
     * 
     * @param partListBinDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String validPtBin(PartListBinListDTO partListBinListDTO, PartListBinDetailDTO partListBinDetailDTO, User user) throws Exception;
    
    /**
     * update BinNo
     * @author sy.yang
     * @version $Id:$
     * @since   1.0
     * 
     * @param partListBinDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateBinNo(PartListBinDetailDTO partListBinDetailDTO, User user) throws Exception;
}
