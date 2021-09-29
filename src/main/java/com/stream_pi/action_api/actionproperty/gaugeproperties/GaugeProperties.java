package com.stream_pi.action_api.actionproperty.gaugeproperties;

import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.Section;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GaugeProperties implements Serializable
{
    private Gauge.SkinType skinType;
    private double minValue;
    private double maxValue;
    private List<GaugeSection> sections;
    private double value;

    private String unit;
    private String subTitle;
    private int decimals;

    private boolean sectionsVisible;
    private SerializableColor foregroundBaseColor = null;
    private SerializableColor barColor = null;


    private static final long serialVersionUID = 2000200419671976L;

    public GaugeProperties(Gauge.SkinType skinType, double minValue, double maxValue, double value,
                           List<GaugeSection> sections, String unit, String subTitle, int decimals,
                           boolean sectionsVisible, SerializableColor foregroundBaseColor, SerializableColor barColor)
    {
        this.skinType = skinType;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.value = value;
        this.sections = sections;
        this.unit = unit;
        this.subTitle = subTitle;
        this.decimals = decimals;
        this.sectionsVisible = sectionsVisible;
        this.foregroundBaseColor = foregroundBaseColor;
        this.barColor = barColor;
    }

    public GaugeProperties()
    {
        this.skinType = Gauge.SkinType.FLAT;
        this.minValue = 0;
        this.maxValue = 100;
        this.value = 0;
        this.sections = new ArrayList<>();
        this.unit = "";
        this.subTitle = "";
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getMaxValue()
    {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    public double getMinValue()
    {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public Gauge.SkinType getSkinType()
    {
        return skinType;
    }

    public void setSkinType(Gauge.SkinType skinType) {
        this.skinType = skinType;
    }

    public ObservableList<Section> getSections()
    {
        ArrayList<Section> tbr = new ArrayList<>();

        sections.forEach(section->{
            tbr.add(new Section(section.getStart(), section.getStop(), Color.color(
                    section.getColor().getRed(), section.getColor().getGreen(),
                    section.getColor().getBlue(), section.getColor().getOpacity()
            )));
        });

        return FXCollections.observableList(tbr);
    }

    public void setSections(List<Section> sections)
    {
        this.sections.clear();
        sections.forEach(section -> this.sections.add(new GaugeSection(section.getStart(), section.getStop(), section.getColor())));
    }

    public String getUnit()
    {
        return unit;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getSubTitle()
    {
        return subTitle;
    }

    public void setSubTitle(String subTitle)
    {
        this.subTitle = subTitle;
    }

    public int getDecimals()
    {
        return decimals;
    }

    public void setDecimals(int decimals)
    {
        this.decimals = decimals;
    }

    public void setSectionsVisible(boolean sectionsVisible) {
        this.sectionsVisible = sectionsVisible;
    }

    public boolean isSectionsVisible() {
        return sectionsVisible;
    }

    public void setBarColor(Color barColor)
    {
        this.barColor = new SerializableColor(barColor);
    }

    public Color getBarColor()
    {
        return barColor.getColor();
    }

    public void setForegroundBaseColor(Color foregroundBaseColor)
    {
        this.foregroundBaseColor = new SerializableColor(foregroundBaseColor);
    }

    public Color getForegroundBaseColor()
    {
        return foregroundBaseColor.getColor();
    }


    public GaugeProperties chaap()
    {
        return new GaugeProperties(getSkinType(), getMinValue(), getMaxValue(), getValue(), sections,
                getUnit(), getSubTitle(), getDecimals(), isSectionsVisible(), foregroundBaseColor, barColor);
    }
}