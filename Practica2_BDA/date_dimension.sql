DROP TABLE IF EXISTS "Date Dimension";
CREATE TABLE "Date Dimension"
(
    "Date Key" serial,
    "Date" date,
    "Full Day Description" text,
    "Day Of Week" text,
    "Calendar Month" text,
    "Calendar Year" integer,
    "Fiscal Year Month" text,
    "Holiday Indicator" text,
    "Weekday Indicator" text
);

INSERT INTO "Date Dimension"
    ("Date", "Full Day Description", "Day Of Week", "Calendar Month",
    "Calendar Year", "Fiscal Year Month", "Holiday Indicator",
    "Weekday Indicator")
SELECT
    day,
    rtrim(to_char(day, 'Month')) || to_char(day, ' DD, YYYY'),
    to_char(day, 'Day'),
    rtrim(to_char(day, 'Month')),
    date_part('year', day),
    'F' || to_char(day, 'YYYY-MM'),
    '', --omitting (trivial 'Holiday'/'Non-Holiday, but how to get this ??),
    CASE
        WHEN date_part('isodow', day) IN (6, 7) THEN 'Weekend'
        ELSE 'Weekday'
    END
FROM
    generate_series('2015-01-01'::date, '2025-12-31'::date, '1 day') day;
