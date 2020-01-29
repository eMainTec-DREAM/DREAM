package dream.asset.list.service;

import java.io.IOException;
import java.util.List;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrCommonDTO;

/**
 * 설비마스터 - 목록 service
 * @author  kim21017
 * @version $Id: MaEqMstrListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaEqMstrListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maEqMstrCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findEqMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO,User user);
    /**
     *  grid find
     * @author  hyosung
     * @version $Id: MaEqMstrListService.java,v 1.0 2015/12/02 09:12:40 hyosung Exp $
     * @param maEqMstrCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws IOException 
     * @throws Exception
     */
    public List findEqMachMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO,User user) throws IOException;
    
    
    /**
     *  grid find
     * @author  hyosung
     * @version $Id: MaEqMstrListService.java,v 1.0 2015/12/02 09:12:40 hyosung Exp $
     * @param maEqMstrCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findEqUtilMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO,User user);
    
    /**
     *  grid find
     * @author  hyosung
     * @version $Id: MaEqMstrListService.java,v 1.0 2015/12/02 09:12:40 hyosung Exp $
     * @param maEqMstrCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findEqBuildMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO,User user);
    
    /**
     *  grid find
     * @author  hyosung
     * @version $Id: MaEqMstrListService.java,v 1.0 2015/12/02 09:12:40 hyosung Exp $
     * @param maEqMstrCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findEqToolMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO,User user);
    /**
     *  grid find
     * @author  hyosung
     * @version $Id: MaEqMstrListService.java,v 1.0 2015/12/02 09:12:40 hyosung Exp $
     * @param maEqMstrCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findEqMoldMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO,User user);
    
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id$
     * @param maEqMstrCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findEqITList(MaEqMstrCommonDTO maEqMstrCommonDTO,User user);
    public String findITTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO,User user);
    
    public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO,User user);
    
    /**
     *  grid find
     * @author  js.lee
     * @version $Id$
     * @param maEqMstrCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findEqGNList(MaEqMstrCommonDTO maEqMstrCommonDTO,User user);
    public String findGNTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO,User user);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaEqMstrListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @return
     * @throws Exception
     */
    public int deleteEqMstr(String[] deleteRows, User loginUser) throws Exception;

	public int insertQrCode(String[] selectRows, String fileName, User loginUser)throws Exception;
	public int insertListQrCode(MaEqMstrCommonDTO maEqMstrCommonDTO, String fileName, User loginUser)throws Exception;

	public List copyEquipment(String[] selectRows, User loginUser)throws Exception;
	/**
	 * Search Part Eq
	 * @param maEqMstrCommonDTO
	 * @param user
	 * @return
	 */
	public List findEqPartMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
	public int insertCopyMstr(String newSeq, String oldSeq, String revisionHistId, String revisionStatus, User user, String isCopy) throws Exception;
	
	
	public String findPtTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
	public String findMcTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
	public String findUtTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
	public String findBdTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
	public String findTlTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
	public String findMdTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);

    public String getData(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
	
}
