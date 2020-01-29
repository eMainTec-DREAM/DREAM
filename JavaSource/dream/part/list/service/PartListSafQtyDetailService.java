package dream.part.list.service;

import common.bean.User;
import dream.part.list.dto.PartListSafQtyDetailDTO;
import dream.part.list.dto.PartListSafQtyListDTO;
/**
 * 부품창고 보관위치 - Detail Service
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 */
public interface PartListSafQtyDetailService
{    
	/**
	 * 부품창고 보관위치 DETAIL
     * @author cjscjs9
	 * @param partListSafQtyListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public PartListSafQtyDetailDTO findPtWhEmpDetail(PartListSafQtyListDTO partListSafQtyListDTO, User user) throws Exception;
	/**
	 * INSERT 부품창고 보관위치 DETAIL
     * @author cjscjs9
	 * @param partListSafQtyDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertPtWhEmpDetail(PartListSafQtyDetailDTO partListSafQtyDetailDTO, User user) throws Exception;
    /**
     * UPDATE 부품창고 보관위치 DETAIL
     * @author cjscjs9
     * @param partListSafQtyDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updatePtWhEmpDetail(PartListSafQtyDetailDTO partListSafQtyDetailDTO, User user) throws Exception;
    
    /**
     * valid empNo 사원 번호 
     * @author cjscjs9
     * @version $Id:$
     * @since   1.0
     * 
     * @param partListSafQtyDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String validEmpNo(PartListSafQtyListDTO partListSafQtyListDTO, PartListSafQtyDetailDTO partListSafQtyDetailDTO, User user) throws Exception;
}
