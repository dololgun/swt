package com.sam.e4.clock.ui.internal;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TimeZoneComparator implements Comparator<TimeZone> {

	@Override
	public int compare(TimeZone o1, TimeZone o2) {

		return o1.getID().compareTo(o2.getID());
	}

	public static Map<String, Set<TimeZone>> getTimeZones() {

		Stream<String[]> sTimeZone = Stream.of(TimeZone.getAvailableIDs())
				.map(s -> s.split("/"))
				.filter(s -> s.length == 2);
		
		return sTimeZone.collect(Collectors.toMap(
				s -> s[0], 
				s -> {
					Set<TimeZone> zones = new TreeSet<TimeZone>(new TimeZoneComparator());
					TimeZone timeZone = TimeZone.getTimeZone(s[0] + "/" + s[1]);
					zones.add(timeZone);
					return zones;
				},
				(oldValue, value) -> {
					oldValue.addAll(value);
					return oldValue;
				},
				TreeMap::new)
				);
	}

}
