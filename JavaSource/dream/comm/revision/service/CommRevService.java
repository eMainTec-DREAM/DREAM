package dream.comm.revision.service;

import java.io.IOException;

import common.bean.User;
import dream.comm.revision.dto.CommRevCommonDTO;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 * 상세 service
 * 
 * @author jung7126
 * @version $Id: CommRevService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
 * @since 1.0
 */
public interface CommRevService
{   
	/**
     * Param 조회
     * @author jung7126
     * @version $Id: CommRevService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param commRevCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public CommRevCommonDTO findDetail(CommRevCommonDTO commRevCommonDTO, User user) throws Exception;
    /**
     * regislate insert
     * @author jung7126
     * @version $Id: WorkPmListRevCreateService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param workPmListRevCreateDTO
     * @return
     * @throws IOException 
     * @throws Exception
     */
    public int insertRegislate(CommRevCommonDTO commRevCommonDTO, User user) throws Exception;
    /**
     * revision insert
     * @author jung7126
     * @version $Id: CommRevService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param commRevCommonDTO
     * @return
     * @throws Exception
     */
    public int insertRevision(CommRevCommonDTO commRevCommonDTO, User user) throws Exception;
    /**
     * Regislate Complete
     * @author jung7126
     * @version $Id: MaPmMstrDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrDetailDTO
     * @return
     * @throws Exception
     */
    public int completeRegislate(CommRevCommonDTO commRevCommonDTO, User user) throws Exception;
    
    public String maxRevNo(CommRevCommonDTO commRevCommonDTO, User user);

    /**
     * Revision Complete
     * @author jung7126
     * @version $Id: MaPmMstrDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrDetailDTO
     * @return
     * @throws Exception
     */
    public String completeRevision(CommRevCommonDTO commRevCommonDTO, User loginUser) throws Exception;
    
    public String validRevNo(CommRevCommonDTO commRevCommonDTO, User user);
    public String validObjectNo(CommRevCommonDTO commRevCommonDTO, User user) throws Exception;
    public String selectCustomObjectNo(CommRevCommonDTO commRevCommonDTO, User user);
    
    public void appProcess(AppReqDetailDTO appReqDetailDTO, User user, String revStatus, String objectType);
    
    public String findEqDesc(CommRevCommonDTO commRevCommonDTO, User user);
    int insertRevHist(CommRevCommonDTO commRevCommonDTO, User user) throws Exception;
    int updateRevHist(CommRevCommonDTO commRevCommonDTO, User user) throws Exception;
}
