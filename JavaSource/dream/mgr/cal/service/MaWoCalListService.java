package dream.mgr.cal.service;

import java.util.List;

import common.bean.User;
import dream.mgr.cal.dto.MaWoCalCommonDTO;
import dream.mgr.cal.form.MaWoCalListForm;

/**
 * Working Calendar - ��� service
 * @author kim21017
 * @version $Id: $
 * @since   1.0
 */
public interface MaWoCalListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: $
     * @param maWoCalCommonDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findList(MaWoCalCommonDTO maWoCalCommonDTO, User user);    
   
    /**
     * �ٹ�����
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param selectRows
     * @return
     * @throws Exception
     */
    public int dayOffList(String compNo, String[] selectRows,User user) throws Exception;
    /**
     * �޹�����
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param selectRows
     * @return
     * @throws Exception
     */
    public int dayOnList(String compNo, String[] selectRows, User user, String[] selectRowsExt) throws Exception;

    /**
     * ����������
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param userNo
     * @param plant
     * @param maWoCalListForm
     * @return
     * @throws Exception
     */
	public int popupSave(String compNo, String userNo, String plant, MaWoCalListForm maWoCalListForm) throws Exception;
	
	public String findTotalCount(MaWoCalCommonDTO maWoCalCommonDTO, User user);
	
}
