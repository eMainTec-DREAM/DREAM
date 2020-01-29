package dream.asset.list.dao;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrCommonDTO;

/**
 * 설비마스터 - 목록 dao
 * @author  kim21017
 * @version $Id: MaEqMstrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqMstrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @return List
     */
    public List findEqMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
    /**
     * grid find
     * @author  hyosung
     * @version $Id: MaEqMstrListDAO.java,v 1.0 2015/12/02 09:14:12 hyosung Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @return List
     * @throws IOException 
     */
    public List findEqMachMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO, User user) throws IOException;
    /**
     * grid find
     * @author  hyosung
     * @version $Id: MaEqMstrListDAO.java,v 1.0 2015/12/02 09:14:12 hyosung Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @return List
     */
    public List findEqUtilMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
    /**
     * grid find
     * @author  hyosung
     * @version $Id: MaEqMstrListDAO.java,v 1.0 2015/12/02 09:14:12 hyosung Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @return List
     */
    public List findEqBuildMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
    
    /**
     * grid find
     * @author  hyosung
     * @version $Id: MaEqMstrListDAO.java,v 1.0 2015/12/02 09:14:12 hyosung Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @return List
     */
    public List findEqToolMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
    /**
     * grid find
     * @author  hyosung
     * @version $Id: MaEqMstrListDAO.java,v 1.0 2015/12/02 09:14:12 hyosung Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @return List
     */
    public List findEqMoldMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
    
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @return List
     */
    public List findEqITList(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
    
    public String  findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
    
    /**
     * grid find
     * @author  js.lee
     * @version $Id$
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @return List
     */
    public List findEqGNList(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaEqMstrListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int updateDeleteTag(String id, User loginUser);
    


	public int insertQrCode(String id, String fileName, User loginUser);
	public int insertListQrCode(MaEqMstrCommonDTO maEqMstrCommonDTO, String fileName, User loginUser);
	public int deleteQrCode(User loginUser, String fileName) throws Exception;
	
	public String getNextSequence(String seqName);
	/**
	 * copy detail insert
	 * @param maEqMstrDetailDTO
	 * @return
	 */
	public int insertCopyDetail(String newId, String oldId, String revisionHistId, String revisionStatus, User user, String isCopy);
	/**
	 * copy eqasmb insert
	 * @param maEqMstrDetailDTO
	 * @return
	 */
	public int insertCopyEqAsmb(String newId, String oldId, User user);
	public int insertCopyEqMold(String newId, String oldId, User user);
	public int insertCopyEqTool(String newId, String oldId, User user);
	public int insertCopyEqBuilding(String newId, String oldId, User user);
	public int insertCopyEqSpec(String newId, String oldId, User user);
	public int insertCopyEqPart(String newId, String oldId, User user);
	public int insertCopyEqAsset(String newId, String oldId, User user);
	public int insertCopyEqItems(String newId, String oldId, User user);
	public int insertCopyEqVendor(String newId, String oldId, User user);
	public int insertCopyEqDevice(String newId, String oldId, User user);
	

	/**
	 * copy eqasmb insert
	 * @param maEqMstrDetailDTO
	 * @return
	 */
	public int insertCopyEqHist(String newId, String oldId, User user);
	
	public void SP_IF_UPD_TXEQUIPMENT(String equipId, User user) throws Exception;
	/**
	 * Find Eq Part
	 * @param maEqMstrCommonDTO
	 * @param user
	 * @return
	 */
	public List findEqPartMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
	
	
	public String findPtTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
	public String findMcTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
	public String findUtTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
	public String findBdTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
	public String findTlTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
	public String findMdTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
	public String findITTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
	public String findGNTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
	
	public List findImgFileList(String oldImageId, User user );
	public List findImgList(String objectId, String objectType, User user);
	public int insertImage(String newEquipId,Map map, User user);
	public int insertImageFile(String newImageId, Map map, User user);
	
	
	public List findDocList(String objectId, String objectType, User user);
	public int insertDoc(String newEquipId,Map map, User user);
	public List findDocFileList(String oldImageId, User user );
	public int insertDocFile(String newImageId, Map map, User user);
	
    public String getData(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
    
    public String getColums(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
    public String getTables(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
    public String getOrderBy(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
    public String getWhere(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
}