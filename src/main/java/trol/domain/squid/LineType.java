package trol.domain.squid;

public enum LineType {
    ACL_OTHER, ACL, HTTP_PORT, HTTP_ACCESS, FOOTER //ACL_OTHER to te acl-e na początku, na których nie pracujemy, a FOOTER to wszystkie inne rzeczy których nie birzemy pod uwagę - one są na końcu pliku
}
