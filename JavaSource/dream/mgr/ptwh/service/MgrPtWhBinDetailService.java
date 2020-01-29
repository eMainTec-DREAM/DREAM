package dream.mgr.ptwh.service;

import common.bean.User;
import dream.mgr.ptwh.dto.MgrPtWhBinDetailDTO;
import dream.mgr.ptwh.dto.MgrPtWhBinListDTO;
/**
 * ��ǰâ�� ������ġ - Detail Service
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 */
public interface MgrPtWhBinDetailService
{    
	/**
	 * ��ǰâ�� ������ġ DETAIL
     * @author cjscjs9
	 * @param mgrPtWhBinListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public MgrPtWhBinDetailDTO findPtWhEmpDetail(MgrPtWhBinListDTO mgrPtWhBinListDTO, User user) throws Exception;
	/**
	 * INSERT ��ǰâ�� ������ġ DETAIL
     * @author cjscjs9
	 * @param mgrPtWhBinDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertPtWhEmpDetail(MgrPtWhBinDetailDTO mgrPtWhBinDetailDTO, User user) throws Exception;
    /**
     * UPDATE ��ǰâ�� ������ġ DETAIL
     * @author cjscjs9
     * @param mgrPtWhBinDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updatePtWhEmpDetail(MgrPtWhBinDetailDTO mgrPtWhBinDetailDTO, User user) throws Exception;
    
    /**
     * valid empNo ��� ��ȣ 
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
