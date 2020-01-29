package dream.part.list.service;

import common.bean.User;
import dream.part.list.dto.PartListSafQtyDetailDTO;
import dream.part.list.dto.PartListSafQtyListDTO;
/**
 * ��ǰâ�� ������ġ - Detail Service
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 */
public interface PartListSafQtyDetailService
{    
	/**
	 * ��ǰâ�� ������ġ DETAIL
     * @author cjscjs9
	 * @param partListSafQtyListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public PartListSafQtyDetailDTO findPtWhEmpDetail(PartListSafQtyListDTO partListSafQtyListDTO, User user) throws Exception;
	/**
	 * INSERT ��ǰâ�� ������ġ DETAIL
     * @author cjscjs9
	 * @param partListSafQtyDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertPtWhEmpDetail(PartListSafQtyDetailDTO partListSafQtyDetailDTO, User user) throws Exception;
    /**
     * UPDATE ��ǰâ�� ������ġ DETAIL
     * @author cjscjs9
     * @param partListSafQtyDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updatePtWhEmpDetail(PartListSafQtyDetailDTO partListSafQtyDetailDTO, User user) throws Exception;
    
    /**
     * valid empNo ��� ��ȣ 
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
