package jysk;

/**
* jysk/ArticleHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from jysk.idl
* czwartek, 12 grudzie� 2013 10:31:55 CET
*/

public final class ArticleHolder implements org.omg.CORBA.portable.Streamable
{
  public jysk.Article value = null;

  public ArticleHolder ()
  {
  }

  public ArticleHolder (jysk.Article initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = jysk.ArticleHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    jysk.ArticleHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return jysk.ArticleHelper.type ();
  }

}
