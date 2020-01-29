package dream.app.doc.service;

import java.util.List;

import dream.app.doc.dto.AppDocReqDTO;

/**
 * �����û
 * @author  javaworker
 * @version $Id: AppDocReqService.java,v 1.2 2013/12/23 06:34:52 pochul2423 Exp $
 * @since   1.0
 */
public interface AppDocReqService
{
    /**
     * �����û(���繮�� �ۼ�)
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
     * ���缱 ��ȸ
     * @author  javaworker
     * @version $Id: AppDocReqService.java,v 1.2 2013/12/23 06:34:52 pochul2423 Exp $
     * @since   1.0
     * 
     * @param appDocReqDTO
     * @return
     */
    List findFlowUserList(AppDocReqDTO appDocReqDTO);

    /**
     * �����û�� default��(����, ����) ��������
     * @author  javaworker
     * @version $Id: AppDocReqService.java,v 1.2 2013/12/23 06:34:52 pochul2423 Exp $
     * @since   1.0
     * 
     * @param appDocReqDTO
     * @return
     */
    String [] findDefaultInform(AppDocReqDTO appDocReqDTO);
}
