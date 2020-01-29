package dream.mgr.cal.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.cal.dao.MaWoCalListDAO;
import dream.mgr.cal.dto.MaWoCalCommonDTO;
import dream.mgr.cal.form.MaWoCalListForm;
import dream.mgr.cal.service.MaWoCalListService;

/**
 * Working Calendar - 목록 serviceimpl
 * @author kim21017
 * @version
 * @since 1.0
 * 
 * @spring.bean id="maWoCalListServiceTarget"
 * @spring.txbn id="maWoCalListService"
 * @spring.property name="maWoCalListDAO" ref="maWoCalListDAO"
 */
public class MaWoCalListServiceImpl implements MaWoCalListService
{
    private MaWoCalListDAO maWoCalListDAO = null;

    public MaWoCalListDAO getMaWoCalListDAO() 
    {
		return maWoCalListDAO;
	}

	public void setMaWoCalListDAO(MaWoCalListDAO maWoCalListDAO) 
	{
		this.maWoCalListDAO = maWoCalListDAO;
	}

	public List findList(MaWoCalCommonDTO maWoCalCommonDTO,User user)
    {      
        return maWoCalListDAO.findList(maWoCalCommonDTO,user);
    }

	public int dayOnList(String compNo, String[] selectRows, User user, String[] selectRowsExt) throws Exception
	{
        int result = 0;

        if(!selectRows.equals(null))
        	for(int i=0;i<selectRows.length; i++)
        	{
        		if(!"Y".equals(selectRowsExt[i])){
        			result = result + maWoCalListDAO.dayOnCalList(compNo, selectRows[i], user);
                    result = result + maWoCalListDAO.dayOnLnList(compNo, selectRows[i], user);
        		}
        	}
        
        return result;
    }
	public int dayOffList(String compNo, String[] selectRows,User user) throws Exception
	{
        int result = 0;

        if(!selectRows.equals(null))	
            for(String id : selectRows)
            {
                result = result + maWoCalListDAO.dayOffList(compNo, id,user);
                //오더삭제, 스케쥴 삭제..
                //c1.wo_type = 'PMI' and c1.period_type = 'D' and c1.cycle < 7 
            }
        
        return result;
    }
	
	public int popupSave(String compNo, String userNo, String plant,  MaWoCalListForm maWoCalListForm) throws Exception
	{
        int result = 0;
        
        String changeDate =maWoCalListForm.getMaWoCalCommonDTO().getPopupChangeDate();
        result = maWoCalListDAO.popupSave(compNo, userNo,plant,changeDate);

        /*if(!selectRows.equals(null))	
            for(String id : selectRows)
            {
                result = result + maWoCalListDAO.dayOnList(compNo, id);
            }*/
        
        return result;
    }

	@Override
	public String findTotalCount(MaWoCalCommonDTO maWoCalCommonDTO, User user)
	{
		return maWoCalListDAO.findTotalCount(maWoCalCommonDTO, user);
	}
}

