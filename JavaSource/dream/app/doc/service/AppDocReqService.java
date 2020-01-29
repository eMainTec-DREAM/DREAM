package dream.app.doc.service;

import java.util.List;

import dream.app.doc.dto.AppDocReqDTO;

/**
 * 결재요청
 * @author  javaworker
 * @version $Id: AppDocReqService.java,v 1.2 2013/12/23 06:34:52 pochul2423 Exp $
 * @since   1.0
 */
public interface AppDocReqService
{
    /**
     * 결재요청(결재문서 작성)
     * @author  javaworker
     * @version $Id: AppDocReqService.java,v 1.2 2013/12/23 06:34:52 pochul2423 Exp $
     * @since   1.0
     * 
     * @param AppDocReqDTO
     * @param flowDtlDTOList 
     * @throws Exception
     */
    String inputAppDocReq(AppDocReqDTO AppDocReqDTO, List flowDtlDTOList);

    /**
     * 결재선 조회
     * @author  javaworker
     * @version $Id: AppDocReqService.java,v 1.2 2013/12/23 06:34:52 pochul2423 Exp $
     * @since   1.0
     * 
     * @param appDocReqDTO
     * @return
     */
    List findFlowUserList(AppDocReqDTO appDocReqDTO);

    /**
     * 결재요청시 default값(제목, 내용) 가져오기
     * @author  javaworker
     * @version $Id: AppDocReqService.java,v 1.2 2013/12/23 06:34:52 pochul2423 Exp $
     * @since   1.0
     * 
     * @param appDocReqDTO
     * @return
     */
    String [] findDefaultInform(AppDocReqDTO appDocReqDTO);
}
