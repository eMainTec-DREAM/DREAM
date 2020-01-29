package dream.mgr.ptwh.service;

import common.bean.User;
import dream.mgr.ptwh.dto.MgrPtWhBinDetailDTO;
import dream.mgr.ptwh.dto.MgrPtWhBinListDTO;
/**
 * 부품창고 보관위치 - Detail Service
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 */
public interface MgrPtWhBinDetailService
{    
	/**
	 * 부품창고 보관위치 DETAIL
     * @author cjscjs9
	 * @param mgrPtWhBinListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public MgrPtWhBinDetailDTO findPtWhEmpDetail(MgrPtWhBinListDTO mgrPtWhBinListDTO, User user) throws Exception;
	/**
	 * INSERT 부품창고 보관위치 DETAIL
     * @author cjscjs9
	 * @param mgrPtWhBinDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertPtWhEmpDetail(MgrPtWhBinDetailDTO mgrPtWhBinDetailDTO, User user) throws Exception;
    /**
     * UPDATE 부품창고 보관위치 DETAIL
     * @author cjscjs9
     * @param mgrPtWhBinDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updatePtWhEmpDetail(MgrPtWhBinDetailDTO mgrPtWhBinDetailDTO, User user) throws Exception;
    
    /**
     * valid empNo 사원 번호 
     * @author cjscjs9
     * @version $Id:$
     * @since   1.0
     * 
     * @param mgrPtWhBinDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String validEmpNo(MgrPtWhBinListDTO mgrPtWhBinListDTO, MgrPtWhBinDetailDTO mgrPtWhBinDetailDTO, User user) throws Exception;
}
