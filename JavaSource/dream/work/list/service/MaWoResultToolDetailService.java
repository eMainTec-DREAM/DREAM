package dream.work.list.service;

import common.bean.User;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultToolDetailDTO;

/**
 * �۾���� ���԰���� ��
 * @author  kim210117
 * @version $Id: MaWoResultToolDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaWoResultToolDetailService
{    
	//����ȸ
    public MaWoResultToolDetailDTO findDetail(String wkOrId, String woToolId,User user)throws Exception;
    //����
    public int updateDetail(MaWoResultToolDetailDTO maWoResultToolDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User user) throws Exception;
    //����
    public int insertDetail(MaWoResultToolDetailDTO maWoResultToolDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception;
    //�������ȸ
    public String getStockQty(MaWoResultToolDetailDTO maWoResultToolDetailDTO, User loginUser);
}
