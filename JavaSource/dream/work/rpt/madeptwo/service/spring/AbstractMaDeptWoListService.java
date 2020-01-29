package dream.work.rpt.madeptwo.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.madeptwo.dto.MaDeptWoListDTO;
import dream.work.rpt.madeptwo.service.MaDeptWoListService;

public abstract class AbstractMaDeptWoListService implements MaDeptWoListService
{
    protected MaDeptWoListService maDeptWoListService;
    
    public AbstractMaDeptWoListService(MaDeptWoListService maDeptWoListService)
    {
    	this.maDeptWoListService = maDeptWoListService;
    }
    
    public abstract List findDeptWoList(MaDeptWoListDTO maDeptWoListDTO, User user);    
    public abstract List findCntChart(MaDeptWoListDTO maDeptWoListDTO);    
    public abstract List findTimeChart(MaDeptWoListDTO maDeptWoListDTO);    

}