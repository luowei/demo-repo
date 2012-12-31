package com.vvvv.xml;

import java.io.*;
import java.util.Date;
import java.util.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;
import javax.xml.parsers.*;

public class MatchTest
{
	private List<Match> list;
	public MatchTest()
	{
		list=new ArrayList<Match>();
	}
	public void setList(List<Match> list)
	{
		this.list=list;
	}
	public List<Match> getList()
	{
		return this.list;
	}
	
	public static void main(String []args) throws Exception
	{
		SAXParserFactory sf=SAXParserFactory.newInstance();
		SAXParser parser=sf.newSAXParser();
		FileInputStream fis=new FileInputStream("./xml/result.xml");
		MatchHandler handler=new MatchHandler();
		parser.parse(fis,handler);
		List<Match> list=handler.getList();
		for(Match match:list)
		{
			System.out.println(match.getFirstTeam().getName()+"\tVS\t @ "
				+match.getSecondTeam().getName()+" @ "+match.getDate());
		}
	}
		
}

class MatchHandler extends DefaultHandler
{
	public static final String TEAM="team";
	public static final String MATCH="match";
	public static final String DATE="date";
	private List<Match> matchList=new ArrayList<Match>();
	private Match lastMatch;
	private String lastValue;
	private Map<String,Object> eleMap=new HashMap<String,Object>();
	
	public List<Match> getList()
	{
		return this.matchList;
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException
	{
		String eleString=new String(ch,start,length).trim();
		if(eleString.length()==0)
		{
			return;
		}
		lastValue=eleString;
	}
	
	@Override
	public void startDocument() throws SAXException
	{
		super.startDocument();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes)  throws SAXException
	{
		String eleName=qName;
		if(eleName.equals(MATCH))
		{
			Match match=(Match)extract(MATCH,lastValue);	//创建任意类型的实例，自已提供
			matchList.add(match);
			lastMatch=match;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)  throws SAXException
	{
		String eleName=qName;
		if(eleName.equals(TEAM))
		{
			Team team=(Team)extract(TEAM,lastValue);
			//System.out.println("---------------"+lastValue);
			Object object=eleMap.get(TEAM);
			if(object!=null)
			{
				lastMatch.setSecondTeam(team);
				eleMap.remove(TEAM);
			}
			else
			{
				eleMap.put(TEAM,team);
				lastMatch.setFirstTeam(team);
			}
		}
		if(eleName.equals(DATE))
		{
			Date date=(Date)extract(DATE,lastValue);
			lastMatch.setDate(date);
		}
	}
	
	@Override
	public void endDocument()  throws SAXException
	{
		super.endDocument();
	}
	
	public <T> T extract(String type,String value)	//返回任意类型
	{
		T t=null;
		if(type.equals(MATCH))
		{
			t=(T) new Match();
		}
		if(type.equals(TEAM))
		{
			Team team=new Team();
			team.setName(value);
			//System.out.println("teamName----------"+team.getName());
			t=(T) team;
		}
		if(type.equals(DATE))
		{
			//t=(T) new Date(DATE);
			String []strings=value.split("-");
			Calendar cal=Calendar.getInstance();
			cal.set(Integer.parseInt(strings[2]),extractMonth(strings[1]),
				Integer.parseInt(strings[0]));
			t=(T) cal.getTime();
		}
		return t;
	}
	
	public int extractMonth(String mon)
	{
		String[] months=new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
		for(int i=0;i<months.length;i++)
		{
			if(mon.equals(months[i]))
			{
				return i;
			}
		}
		return 0;
	}
}


class Match
{
	private Date date;
	private Team firstTeam;
	private Team secondTeam;
	public Date getDate()
	{
		return this.date;
	}
	public void setDate(Date date)
	{
		this.date=date;
	}
	
	public Team getFirstTeam()
	{
		return this.firstTeam;
	}
	public void setFirstTeam(Team team)
	{
		this.firstTeam=team;
	}
	
	public Team getSecondTeam()
	{
		return this.secondTeam;
	}
	public void setSecondTeam(Team team)
	{
		this.secondTeam=team;
	}
	
	@Override
	public String toString()
	{
		return "Match [firstTeam="+firstTeam+" secondTeam="+secondTeam+"]";
	}
}

class Team
{
	private int score;
	private String name;
	
	public int getScore()
	{
		return this.score;
	}
	public void setScore(int score)
	{
		this.score=score;
	}
	
	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	
	@Override
	public String toString()
	{
		return "Team [score="+score+" name="+name+"]";
	}
}
