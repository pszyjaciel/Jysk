package jysk;

/**
* jysk/PalletHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from jysk.idl
* czwartek, 12 grudzie� 2013 10:31:55 CET
*/

public final class PalletHolder implements org.omg.CORBA.portable.Streamable
{
  public jysk.Pallet value = null;

  public PalletHolder ()
  {
  }

  public PalletHolder (jysk.Pallet initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = jysk.PalletHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    jysk.PalletHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return jysk.PalletHelper.type ();
  }

}
